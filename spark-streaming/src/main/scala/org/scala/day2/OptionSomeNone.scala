package org.scala.day2

object OptionSomeNone {

  def main(args: Array[String]): Unit = {

    val greeting:Option[String] = Some("hello scala")
    val absentGreeting:Option[String] = Option(null)
    val presentGreeting:Option[String] = Option("hello")

    println(greeting)
    println(absentGreeting)
//    println(presentGrezeting)
  }

}
