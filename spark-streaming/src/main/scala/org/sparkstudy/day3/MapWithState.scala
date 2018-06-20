package org.sparkstudy.day3

import org.apache.spark.{SparkConf, SparkContext}

/**
  *  updateStateByKey is executed on the whole range of keys in DStream.
  *  As results performance of these operation is proportional to the size of the state
  *  mapWithState is executed only on set of keys that are available in the last micro batch.
  *  As result performance is proportional to the size of the batch
  *
  */
object MapWithState {

  def main(args: Array[String]): Unit = {

    import org.apache.spark.streaming.{StreamingContext, Seconds}
    var config = new SparkConf().setAppName("MapWithStateApp").setMaster("local[2]")
    val sc = new SparkContext(config)
    val ssc = new StreamingContext(sc, batchDuration = Seconds(5))

    // checkpointing is mandatory
    ssc.checkpoint("D:/spark_out1/_checkpoints")

    // 并行集合是通过调用SparkContext的parallelize方法,在一个已经存在的Scala集合上创建的。
    // 集合的对象将会被拷贝,创建出一个可以被并行操作的分布式数据集.
    val rdd = sc.parallelize(0 to 9).map(n => (n, n % 2 toString))
    import org.apache.spark.streaming.dstream.ConstantInputDStream
    //ConstantInputDStream子类，标示每个时间点都返回相同的RDD，主要用于测试.
    val sessions = new ConstantInputDStream(ssc, rdd)
    import org.apache.spark.streaming.{State, StateSpec, Time}
    val updateState = (batchTime: Time, key: Int, value: Option[String], state: State[Int]) => {
      println(s">>> batchTime = $batchTime")
      println(s">>> key       = $key")
      println(s">>> value     = $value")
      println(s">>> state     = $state")
      val sum = value.getOrElse("").size + state.getOption.getOrElse(0)
      state.update(sum)
      Some((key, value, sum)) // mapped value
    }
    val spec = StateSpec.function(updateState)
    val mappedStatefulStream = sessions.mapWithState(spec)
    mappedStatefulStream.print()
    ssc.start()
    ssc.awaitTermination()

  }

}
