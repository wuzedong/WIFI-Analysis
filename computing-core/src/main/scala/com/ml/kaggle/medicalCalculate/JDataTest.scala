package com.ml.kaggle.medicalCalculate

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.types.IntegerType
/**
  * Created by Administrator on 2018/5/13.
  */
object JDataTest {


  def main(args: Array[String]): Unit = {
    val spark=SparkSession.builder().appName("name")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._
    spark.sparkContext.setLogLevel("WARN")
    val jDataTest=new JDataTest(spark)
    val basePath="E:\\dataset\\JData_UserShop\\"
    val sku="jdata_sku_basic_info.csv"
    val user_basic="jdata_user_basic_info.csv"
    val user_action="jdata_user_action.csv"
    val user_order="jdata_user_order.csv"
    val user_comment="jdata_user_comment_score.csv"
    val data=jDataTest.getSourceData(basePath+user_basic)
data.printSchema()
    data.show(false)
//    println(data.count())
//    println(data.na.drop().count())
    data.select($"user_lv_cd").groupBy($"user_lv_cd").count().show(false)
    val max2min=data.select($"age".as[Int]).collect()
    println("max:"+max2min.max)
    println("min:"+max2min.min)
//    data.select($"para_2").groupBy($"para_2").count().show(false)
//    data.select($"para_3").groupBy($"para_3").count().show(false)

//查看空值


  }
  }

  class JDataTest(spark:SparkSession){

  def getSourceData(path:String): DataFrame ={
    val data=spark.read.option("header","true")
      .option("nullValue","NA")
      .option("inferSchema","true")
        .csv(path)
    data
  }

}