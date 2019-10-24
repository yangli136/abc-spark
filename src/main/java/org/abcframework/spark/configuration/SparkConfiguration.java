package org.abcframework.spark.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:spark.properties")
public class SparkConfiguration {

  @Value("${spark.app.name}")
  private String appName;

  @Value("${spark.master}")
  private String masterUri;

  @Bean
  public SparkConf conf() {
    return new SparkConf().setAppName(appName).setMaster(masterUri);
  }

  @Bean
  public JavaSparkContext sc() {
    return new JavaSparkContext(conf());
  }

  @Override
  public String toString() {
    return "SparkConfiguration [appName=" + appName + ", masterUri=" + masterUri + "]";
  }
}
