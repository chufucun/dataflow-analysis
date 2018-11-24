#!/usr/bin/env python
# -*- coding:utf-8 -*

from collections.abc import Iterable, Iterator, Generator

# 扩展知识:
# 迭代器，是其内部实现了，__next__ 这个魔术方法。(Python3.x)
# 可以通过，dir()方法来查看是否有__next__来判断一个变量是否是迭代器的。
class MyList(object):  # 定义可迭代对象类
    def __init__(self, num):
        self.end = num  # 上边界

    # 返回了一个实现了__iter__和__next__的迭代器类的实例
    # "__xx__"前后各双下划线,简单说下魔术方法的概念，python 语言的一种语法特性，可以让一些函数不用被显式的调用的时候被执行
    def __iter__(self):
        return MyListIterator(self.end)


class MyListIterator(object):
    def __init__(self, end):
        self.data = end
        self.start = 0

    def __iter__(self):
        return self

    def __next__(self):
        while self.start < self.data:
            self.start += 1
            return self.start - 1
        # 当程序出现错误，python会自动引发异常，也可以通过raise显示地引发异常。一旦执行了raise语句，raise后面的语句将不能执行。
        raise StopIteration


if __name__ == '__main__':
    my_list = MyList(5)
    print(isinstance(my_list, Iterable))
    print(isinstance(my_list, Iterator))

    for i in my_list:
        print(i)


    my_iterator = iter(my_list)  # 得到一个迭代器
    print(isinstance(my_iterator, Iterable))  # True
    print(isinstance(my_iterator, Iterator))  # True

    # 迭代
    print(next(my_iterator))
    print(next(my_iterator))
    print(next(my_iterator))
    print(next(my_iterator))
    print(next(my_iterator))
