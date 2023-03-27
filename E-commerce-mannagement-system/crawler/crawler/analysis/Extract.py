# 提取爬取到的内容
from analysis.format import FormatData

# 数据格式转换对象
_format = FormatData()


class Extract(object):

    # 解析商品种类
    @staticmethod
    def get_tabs(items):
        # 定义一个空的列表
        tabs = []
        # 遍历爬取到的list列表
        for item in items:
            print("商品种类编号--", item['id'], "商品种类名称--", item['name'])
            # 将遍历到的内容一个一个添加到空列表中
            tabs.append({
                'tab_id': _format.null_to_int(item['id']),
                'name': _format.null_to_str(item['name']),
                'state': 1,
            })
        return tabs

    # 解析商品信息
    @staticmethod
    def get_products(items):
        product_list = []
        for item in items:
            product_list.append({
                'image': _format.null_to_str(item['image']),
                'sale_num': _format.null_to_int(item['saleNum']),
                'origin_price': _format.null_to_int(item['originPrice']),
                'product__id': item['id'],
                'commodity__id': _format.null_to_str(item['commodityId']),
                'title': _format.null_to_str(item['title']),
                'price': item['price'],
                'is_free_postage': _format.bool_to_int(item['isFreePostage']),
                'url': _format.null_to_str(item['url']),
                'state': 1,
            })
        return product_list

    # 解析商店信息
    @staticmethod
    def get_shops(item):
        shop = []
        shop.append({
            'good_rate_percentage': _format.null_to_int(item['goodRatePercentage']),  # 浮点型
            'service_score': _format.null_to_str(item['serviceScore']),
            'delivery_score': _format.null_to_str(item['deliveryScore']),
            'all_item_count': _format.null_to_int(item['allItemCount']),
            'title': _format.null_to_str(item['title']),
            'fans_num': _format.null_to_int(item['fansNum']),
            'pic_url': _format.null_to_str(item['picUrl']),
            'shop_id': _format.null_to_int(item['shopId']),
            'state': 1,
        })
        return shop

    # 解析detail信息
    @staticmethod
    def get_details(item):
        detail = []
        detail.append({
            'product_id': _format.null_to_int(item['id']),
            # 从shop中取出shopId
            'shop_id': _format.null_to_str(item['shop']['shopId']),
            'commodity_id': _format.null_to_str(item['commodityId']),
            'coupon_value': _format.null_to_str(item['couponValue']),
            'expire_msg': _format.null_to_str(item['expireMsg']),
            'expire_date': (item['expireDate']),
            'url': _format.null_to_str(item['url']),
            'state': 1,
        })
        return detail
