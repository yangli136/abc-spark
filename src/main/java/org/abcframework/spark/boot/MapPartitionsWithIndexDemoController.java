package org.abcframework.spark.boot;

import org.abcframework.spark.service.MapPartitionsWithIndexDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnProperty(name = "web.test.enabled", havingValue = "true", matchIfMissing = false)
@RestController
public class MapPartitionsWithIndexDemoController {

  @Autowired MapPartitionsWithIndexDemoService service;

  @GetMapping(path = "/mapPartitionsWithIndex")
  public String count() {
    return service.getCount("mapPartitionsWithIndex.txt").toString();
  }
}
