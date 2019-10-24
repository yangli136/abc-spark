package org.abcframework.spark.boot;

import org.abcframework.spark.service.AccumulatorDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnProperty(name = "web.test.enabled", havingValue = "true", matchIfMissing = false)
@RestController
public class AccumulatorDemoController {

  @Autowired AccumulatorDemoService service;

  @GetMapping(path = "/accumulator")
  public long count() {
    return service.getCount();
  }
}
