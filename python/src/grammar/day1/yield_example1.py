#!/usr/bin/env python
# -*- coding:utf-8 -*

'''
    这是简单测试的例子, 关于生成器\迭代器
'''
# 斐波那契数列
def fab(max):
    n, a, b = 0, 0, 1
    while n < max:
        yield b
        a, b = b, a + b
        n = n + 1


def fab2(max):
    n, a, b = 0, 0, 1
    while n < max:
        print(b)
        a, b = b, a + b
        n = n + 1


def iterate():
    list = [1, 2, 3, 4]
    it = iter(list)
    for x in it:
        print(x, end=" ")


if __name__ == '__main__':
    print(type(fab(6)))
    print(type(fab2(6)))
    print(type(fab2))

    iterate()

