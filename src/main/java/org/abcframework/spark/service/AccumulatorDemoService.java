package org.abcframework.spark.service;

import java.util.Arrays;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.util.LongAccumulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccumulatorDemoService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AccumulatorDemoService.class);

  @Autowired JavaSparkContext sc;

  public long getCount() {
    final LongAccumulator counter = sc.sc().longAccumulator();
    List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    JavaRDD<Integer> rdd = sc.parallelize(data);

    rdd.foreach(
        x -> {
          counter.add(x);
          LOGGER.info(
              Thread.currentThread().getName() + "-Inter Counter value:{}", counter.value());
        });

    LOGGER.info("### ### ### Counter value:{}", counter.value());
    return counter.value();
  }
}
