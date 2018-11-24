package grammer.day2

/**
  * Scala Option(选项)类型用来表示一个值是可选的（有值或无值)
  * Option[T] 是一个类型为 T 的可选值的容器： 如果值存在， Option[T] 就是一个 Some[T] ，如果不存在， Option[T] 就是对象 None 。
  * Scala 程序使用 Option 非常频繁，在 Java 中使用 null 来表示空值，代码中很多地方都要添加 Null 检测，
  * 不然很容易出现 NullPointException。 因此 Java 程序需要关心那些变量可能是 Null，
  * 而这些变量出现 Null 的可能性很低，但一但出现，很难查出为什么出现 NullPointerException。
  *
  * Scala 的 Option 类型可以避免这种情况，因此 Scala 应用推荐使用 Option 类型来代表一些可选值。
  * 使用 Option 类型，读者一眼就可以看出这种类型的值可能为 None。
  *
  */
object OptionSomeNone {

  val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo", "China" -> "Beijing")

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

  def optionGreeting(): Unit = {
    val greeting: Option[String] = Some("hello scala")
    val absentGreeting: Option[String] = Option(null)
    val presentGreeting: Option[String] = Option("hello")

    println(greeting)
    println(absentGreeting)
    println(presentGreeting)
  }


  def main(args: Array[String]): Unit = {

    // 1、 Option 作为可选值
    optionGreeting

    // 2、Option 类型的值通常作为 Scala 集合类型（List，Map 等）操作的返回类型
    println(capitals get "China")

    // 3、将Option 类型的值放开的一种常见的方法是使用模式匹配
    println(show(capitals get "China"))

  }
}
