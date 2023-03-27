import os

from simpysql.DBModel import DBModel


class Tab(DBModel):
    __basepath__ = os.path.abspath(os.path.dirname(os.path.dirname(__file__))) + '/'
    __database__ = 'default'
    __tablename__ = 'tabs'
    columns = [
        'tab_id',
        'name',
        'state',
    ]
