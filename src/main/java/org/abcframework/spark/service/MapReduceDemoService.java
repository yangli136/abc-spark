package org.abcframework.spark.service;

import org.abcframework.spark.service.util.FileUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapReduceDemoService {

  @Autowired JavaSparkContext sc;

  public int getCount(String fileName) {
    final String fullPath = FileUtils.getFullFilePath(fileName);
    final JavaRDD<String> lines = sc.textFile(fullPath);
    JavaRDD<Integer> lineLengths = lines.map(String::length);
    return lineLengths.reduce((a, b) -> a + b);
  }
}
