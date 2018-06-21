--
-- Created by IntelliJ IDEA.
-- User: tianhonghan
-- Date: 2018/6/21
-- Time: 15:42
-- To change this template use File | Settings | File Templates.
--

print(type("Hello world"))      --> string
print(type(10.4*3))             --> number
print(type(print))              --> function
print(type(type))               --> function
print(type(true))               --> boolean
print(type(nil))                --> nil
print(type(type(X)))            --> string

co = coroutine.create(function (value1,value2)
    local tempvar3 =10
    print("coroutine section 1", value1, value2, tempvar3)
    local tempvar1 = coroutine.yield(value1+1,value2+1)
    tempvar3 = tempvar3 + value1
    print("coroutine section 2",tempvar1 ,tempvar2, tempvar3)
    local tempvar1, tempvar2= coroutine.yield(value1+value2, value1-value2)
    tempvar3 = tempvar3 + value1
    print("coroutine section 3",tempvar1,tempvar2, tempvar3)
    return value2, "end"
end)

print("main", coroutine.resume(co, 3, 2))
print("main", coroutine.resume(co, 12,14))
print("main", coroutine.resume(co, 5, 6))
print("main", coroutine.resume(co, 10, 20))