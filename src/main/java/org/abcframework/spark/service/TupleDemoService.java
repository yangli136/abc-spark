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

	@Autowired
	JavaSparkContext sc;

	public String getWordCountResult(String fileName) {
		final String fullPath = FileUtils.getFullFilePath(fileName);
		final JavaRDD<String> lines = sc.textFile(fullPath);
		final JavaPairRDD<String, Integer> pairs = lines.mapToPair(s -> new Tuple2<String, Integer>(s, 1));
		final JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b);

		return retrieveResultAsString(counts);
	}

	private String retrieveResultAsString(JavaPairRDD<String, Integer> counts) {
		final List<Tuple2<String, Integer>> tuplesRetured = counts.collect();
		final StringBuilder allTuples = new StringBuilder();
		int[] i = { 0 };
		tuplesRetured.stream().forEach(x -> allTuples.append(String.valueOf(i[0]++)).append("-{").append(x._1())
				.append(":").append(x._2()).append("}").append(System.lineSeparator()));
		return allTuples.toString();
	}
}
