import time


class FormatData(object):
    @staticmethod
    def null_to_str(_str):
        if _str is None:
            return ''
        else:
            return _str

    @staticmethod
    def null_to_int(_int):
        if _int is None:
            return 0
        else:
            return _int

    @staticmethod
    def bool_to_int(_bool):
        if _bool:
            return 1
        else:
            return 0

    @staticmethod
    def null_to_time(_time):
        if _time is (0 or None):
            return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
        else:
            return _time


