package org.abcframework.spark.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.abcframework.spark.service.util.FileUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapPartitionsWithIndexDemoService {

  @Autowired JavaSparkContext sc;

  public List<Map<Integer, List<Integer>>> getCount(String fileName) {
    final String fullPath = FileUtils.getFullFilePath(fileName);
    JavaRDD<String> lines = sc.textFile(fullPath);

    JavaRDD<List<String>> wordList =
        lines.map(
            s -> {
              List<String> tempWordList = new ArrayList<>();
              try (Scanner scanner = new Scanner(s)) {
                while (scanner.hasNext()) {
                  tempWordList.add(scanner.next());
                }
              }
              return tempWordList;
            });

    JavaRDD<List<String>> allWordListPartiioned = wordList.repartition(8);

    JavaRDD<Map<Integer, List<Integer>>> results =
        allWordListPartiioned.mapPartitionsWithIndex(
            (index, s) -> {
              List<Map<Integer, List<Integer>>> indexWordListMapList = new ArrayList<>();
              while (s.hasNext()) {
                List<String> tempWordList = s.next();
                List<Integer> indexList = new ArrayList<>();
                Map<Integer, List<Integer>> indexWordListMap = new HashMap<>();
                tempWordList.forEach(w -> indexList.add(w.length()));
                indexWordListMap.put(index, indexList);
                indexWordListMapList.add(indexWordListMap);
              }

              return indexWordListMapList.iterator();
            },
            true);
    //		JavaRDD<List<Integer>> results = allWordList.mapPartitions(s -> {
    //			List<List<Integer>> intList = new ArrayList<>();
    //			while (s.hasNext()) {
    //				List<String> wordList = s.next();
    //				List<Integer> inList = new ArrayList<>();
    //				wordList.forEach(w -> inList.add(w.length()));
    //				intList.add(inList);
    //			}
    //
    //			return intList.iterator();
    //		});
    return results.collect();
  }
}
