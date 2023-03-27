import os

from simpysql.DBModel import DBModel


class Detail(DBModel):
    __basepath__ = os.path.abspath(os.path.dirname(os.path.dirname(__file__))) + '/'
    __database__ = 'default'
    __tablename__ = 'details'
    columns = [
        'id',
        'product_id',
        'shop_id',
        'commodity_id',
        'coupon_value',
        'expire_msg',
        'expire_date',
        'url',
        'state',
    ]

