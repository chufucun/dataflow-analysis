package org.sparkstudy.day3

import java.nio.charset.StandardCharsets

import org.apache.nifi.remote.client.SiteToSiteClient
import org.apache.nifi.spark.NiFiReceiver
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.DStream
import com.huaban.analysis.jieba.JiebaSegmenter
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode

object TaptapJieba {

  def segmentProcess(segment: DStream[String]): Unit = {

    segment.print()
    val wordcount = segment.flatMap { w =>
      var word = if (w.length > 0)
        new JiebaSegmenter().sentenceProcess(w)
//      println(word.toString)
      word.toString
    }.map(w => (w, 1)).reduceByKey(_ + _)

    //将词频统计的结果进行一次元组位置的交换，将频次放到第一个元素，而单词放到第二个元素
    val inputRDDv5 = wordcount.map(x => x.swap)
    //交换后进行按频次排序，参数ascending=False表示降序排列
    val inputRDDv6 = inputRDDv5.transform(_.sortByKey(false))
    //提取前10的元素
    println("============================================"+inputRDDv6.print(100))

//    val top100 = inputRDDv6.foreachRDD(rdd => {
//      val toplist = rdd.take(100)
//      toplist.foreach {
//        case (count, word) => println("%s : %s".format(word, count))
//      }
//    }
//    )

  }

  def main(args: Array[String]): Unit = {
    val conf = new SiteToSiteClient.Builder().url("http://localhost:8080/nifi").portName("sparkstreaming").requestBatchCount(1).buildConfig()
    val config = new SparkConf().setAppName("TaptapJiebaTF").setMaster("local[2]")
    val ssc = new StreamingContext(config, batchDuration = Seconds(5))

    val nifiStreams = (1 to 1).map(i => ssc.receiverStream(new NiFiReceiver(conf, StorageLevel.MEMORY_ONLY)))
    val lines = ssc.union(nifiStreams)
    val lineStrings = lines.map(dataPacket => new String(dataPacket.getContent, StandardCharsets.UTF_8))
    //    lineStrings.print()

    segmentProcess(lineStrings)

    ssc.start()
    ssc.awaitTermination()
  }
}
