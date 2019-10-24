package org.abcframework.spark.boot;

import org.abcframework.spark.service.MapReduceDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnProperty(name = "web.test.enabled", havingValue = "true", matchIfMissing = false)
@RestController
public class MapReduceDemoController {

  @Autowired MapReduceDemoService service;

  @GetMapping(path = "/mapReduce")
  public int count() {
    return service.getCount("mapReduce.txt");
  }
}
