package org.abcframework.spark.boot.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MethodReference {

  public static void main(String[] args) {
    List<Shipment> l = new ArrayList<>();
    MethodReference mf = new MethodReference();
    List<Double> results = mf.calculateOnShipments(l, Shipment::calculateWeight);
    System.out.println(results);
  }

  public List<Double> calculateOnShipments(List<Shipment> l, Function<Shipment, Double> f) {
    List<Double> results = new ArrayList<>();
    for (Shipment s : l) {
      results.add(f.apply(s));
    }
    return results;
  }
}
