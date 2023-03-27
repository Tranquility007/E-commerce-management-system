import os

from simpysql.DBModel import DBModel


class Product(DBModel):
    __basepath__ = os.path.abspath(os.path.dirname(os.path.dirname(__file__))) + '/'
    __database__ = 'default'
    __tablename__ = 'products'
    columns = [
        'image',
        'sale_num',
        'origin_price',
        'product_id',
        'commodity_id',
        'title',
        'price',
        'is_free_postage',
        'url',
        'tab_id',
        'state',
    ]

