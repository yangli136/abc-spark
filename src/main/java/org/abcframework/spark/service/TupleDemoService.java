package org.abcframework.spark.service;

import java.util.List;
import org.abcframework.spark.service.util.FileUtils;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;

@Service
public class TupleDemoService {

  @Autowired JavaSparkContext sc;

  public void getCount(String fileName) {
    final String fullPath = FileUtils.getFullFilePath(fileName);
    JavaRDD<String> lines = sc.textFile(fullPath);
    JavaPairRDD<String, Integer> pairs = lines.mapToPair(s -> new Tuple2(s, 1));
    JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b);
    List<Tuple2<String, Integer>> results = counts.collect();
    counts.foreach(x -> System.out.println(x._1() + x._2()));
  }
}
