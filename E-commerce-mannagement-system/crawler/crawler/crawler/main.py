import pymysql
import requests
import json
import random
from multiprocessing import Process
from numpy.ma import count
from pymysql import IntegrityError
from models.Products import Product
from models.Shops import Shop
from models.Details import Detail
from analysis.Extract import Extract
from models.Tabs import Tab


# 请求头
user_agent = [
    'Mobile Safari/537.36 SE 2.X MetaSr 1.0',
    'Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1',
    'Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11'
]

headers = {
    'Accept': 'application/json, test/plain, */*',
    'User-Agent': random.choice(user_agent),
}

# 创建解析对象
extract = Extract()

# 请求商品分类信息


def crawling_tabs():
    # 种子URL
    url = "http://www.xiongmaoyouxuan.com/api/tabs?sa="
    header = {
        'Accept': 'application/json, test/plain, */*',
        'User-Agent': random.choice(user_agent),
        'Referer': 'http://www.xiongmaoyouxuan.com/',
    }
    # 爬取商品信息
    # 返回的是字符串格式
    response = requests.request("GET", url, headers=header)
    # 将字符串格式转换为json
    json_tabs = response.json()
    # 获取商品种类列表
    tabs = json_tabs['data']['list']
    # 提取商品类别字段
    tab_list = extract.get_tabs(tabs)
    for tab in tab_list:
        try:
            # 保存商品类别
            Tab.create(tab)
        except pymysql.err.IntegrityError:
            pass
    print("成功保存商品种类到数据库...")
    # 提取种类id用于爬取商品信息
    ids = []
    for t in tabs:
        ids.append(t['id'])
    return ids

# 请求产品信息
# start_index：开始爬取的商品位置索引


def crawling_products(start_index=0, tabs_id=1):
    url = "http://www.xiongmaoyouxuan.com/api/tab/{tabs_id}/feeds?start={start_index}&sort=0".format(
        start_index=start_index, tabs_id=tabs_id)
    # 创建session对象，实现对同一个主机的请求可以复用 TCP 连接从而提升速度。
    session = requests.Session()
    # response中存放的文本是字符串格式
    response = session.get(url, headers=headers)
    # 将字符串转成json
    json_products = response.json()
    # 总共的商品数量
    products_count = json_products['data']['total']
    # list中存放商品信息
    products = json_products['data']['list']
    return products_count, products

# 根据product_id爬取details


def crawling_shops(product_id):
    url = "http://www.xiongmaoyouxuan.com/api/detail?id={product_id}&normal=1&sa=".format(product_id=product_id)
    session = requests.Session()
    response = session.get(url, headers=headers)
    json_data = response.json()
    # 取出data中的detail：detail中包含商店的信息
    json_details = json_data['data']['detail']
    # 取出detail中的shop：shop是商店的信息
    json_shops = json_data['data']['detail']['shop']
    return json_details, json_shops

# 分页爬取商品数据


def crawling_products_more(products_count, tab_id):
    # 从第21个开始爬取，每次爬取20个商品，总共爬取total个
    for index in range(20, products_count, 20):
        try:
            # 获取商品信息列表
            print("传入的商品种类编号：", tab_id)
            print("根据商品种类编号爬取商品")
            counts, products = crawling_products(index, tab_id)
            # 将数据字段从json中提取出来
            product_list = extract.get_products(products)
            print("商品信息：", product_list)
            print("-----------------------------")
            # 调用save_all()方法，保存商品、店铺和details到数据库
            save_all(product_list)
        except json.decoder.JSONDecodeError:
            pass
        except TypeError:
            pass

# 保存商品到数据库


def save_all(product_list):
    # 遍历商品列表
    for product in product_list:
        @Tab.transaction
        def demo(item):
            product_id = item['product__id']
            # 传递product_id用于构造url，爬取商店信息
            print("传入的商品id：", product_id)
            json_details, json_shops = crawling_shops(product_id)
            # 提取商店的信息
            shop = extract.get_shops(json_shops)
            print("店铺信息", shop)
            print("-------------------------------")
            # 提取detail的信息
            detail = extract.get_details(json_details)
            # 添加事务，保存三个表
            try:
                Detail.create(detail)
                Product.create(product)
                Shop.create(shop)
            except IntegrityError:  # 拒绝重复数据的时候不报错
                pass
            except pymysql.err.InternalError:
                pass
            except TypeError:
                pass
            return True
        demo(product)
        print("成功保存店铺和商品数据到数据库...")

# 遍历传过来的tab_id列表，根据tab_id爬取商品


def work(tab_id_list):
    for _id in tab_id_list:
        # 获取商品信息
        products_count, products = crawling_products(_id)
        # 分页爬取，根据商品id获取店铺等信息，事务保存
        crawling_products_more(products_count, _id)

# 多进程爬取


if __name__ == '__main__':
    # 获取种类列表
    tab_list = crawling_tabs()
    _process = []
    # 多进程获取并保存商品和店铺
    for i in range(1, count(tab_list)):  # 总共有15个种类
        # target目标函数，args是含有tab_id的列表类型
        # 每次取两个索引值对应的tab_id
        _process.append(Process(target=work, args=(tab_list[i:i + 1],)))
    for p in _process:
        p.start()

