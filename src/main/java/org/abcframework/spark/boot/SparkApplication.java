package org.abcframework.spark.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
    basePackages = {"org.abcframework.spark"},
    excludeFilters =
        @ComponentScan.Filter(
            type = FilterType.REGEX,
            pattern = "org\\.abcframework\\..\\.demo..*"))
@SpringBootApplication
public class SparkApplication {

  public static void main(final String args[]) {
    SpringApplication.run(SparkApplication.class);
  }
}
