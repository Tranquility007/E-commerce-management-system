#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os

from simpysql.DBModel import DBModel



class Shop(DBModel):
    __basepath__ = os.path.abspath(os.path.dirname(os.path.dirname(__file__))) + '/'
    __database__ = 'default'
    __tablename__ = 'shops'
    columns = [
        'good_rate_percentage',
        'service_score',
        'delivery_score',
        'title',
        'all_item_count',
        'fans_num',
        'pic_url',
        'shop_id',
        'state',
    ]