#!/usr/bin/env python
# -*- coding:utf-8 -*

# 创建生成器 (Abstract Base Classes for Containers)
from collections.abc import Generator
from inspect import getgeneratorstate


def createGenerator(num):
    L = (x * x for x in range(num))
    print(isinstance(L, Generator))
    print(dir(L))


def myGenerator(num):
    now = 0
    while now < num:
        yield now
        now += 1
    raise StopIteration  # 我们也应该在不满足生成元素条件的时候，抛出异常。


def jumping_range(N):
    index = 0
    while index < N:
        # yield index 是将index return给外部调用程序。
        # jump = yield 可以接收外部程序通过send()发送的信息，并赋值给jump.
        jump = yield index
        if jump is None:
            jump = 1
        index += jump
    raise StopIteration


if __name__ == '__main__':
    createGenerator(10)

    gen = myGenerator(10)
    # for g in gen:
    # print(g)
    print(getgeneratorstate(gen))
    print(next(gen))
    print(getgeneratorstate(gen))
    print(next(gen))
    gen.close()  # 手动关闭/结束生成器
    print(getgeneratorstate(gen))

    # 如何向生成器中发送消息
    itr = jumping_range(5)
    print(next(itr))
    print(itr.send(2))
    print(next(itr))
    print(itr.send(-1))