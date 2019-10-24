import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import org.abcframework.spark.configuration.SparkConfiguration;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {SparkConfiguration.class})
public class MapPartitionsWithIndexTests {
  @Autowired JavaSparkContext sc;

  @Test
  public void mapPartitionsWithIndex() {
    JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4), 2);

    JavaRDD<Integer> partitionSums =
        rdd.mapPartitionsWithIndex(
            (index, iter) -> {
              int sum = 0;
              while (iter.hasNext()) {
                sum += iter.next();
              }
              return Collections.singletonList(sum).iterator();
            },
            false);
    assertEquals("[3, 7]", partitionSums.collect().toString());
  }
}
