package grammer.day2

/**
  * 知识点:
  * 1."<-" 用于遍历集合对象(可遍历对象)B，在每次遍历的过程中，生成一个新的对象A，这个A是val，
  * 而不是var，然后对循环体中对A进行处理，<-在Scala中称为generator。
  * 不需要显式的指定A的类型，因为Scala使用自动推导的方式根据B的元素类型得出A的类型
  *
  * 2.
  *
  */
object ReCurrent {

  // 在scala中，public默认的可见级别
  def whileLoop: Unit = {
    var i = 1;
    while (i <= 3) {
      println(i)
      i += 1
    }
  }

  def forLoop: Unit = {
    val args = Array("its", "all", "in", "the", "grind")
    for (i <- 0 until args.length) {
      println(args(i))
    }
  }

  /**
    *  循环(loop) - 最基础的概念, 所有重复的行为
    *  递归(recursion) - 在函数内调用自身, 将复杂情况逐步转化成基本情况
    *  (数学) 迭代(iterate) - 在多次循环中逐步接近结果
    *  (编程) 迭代(iterate) - 按顺序访问线性结构中的每一项
    *  遍历(traversal) - 按规则访问非线性结构中的每一项
    *
    *  我们会更多的使用这种迭代方法而不是其他命令式风格的循环.
    */
  def forEachLoop: Unit = {
    val args = Array("its", "all", "in", "the", "grind")
    args.foreach { arg =>
      println(arg)
    }
  }

  def main(args: Array[String]): Unit = {
    whileLoop
    forLoop
    forEachLoop
  }

}



