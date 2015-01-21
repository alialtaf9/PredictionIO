package io.prediction.examples.java.SentimentAnalysis;

import io.prediction.controller.java.EmptyParams;
import io.prediction.controller.java.IJavaEngineFactory;
import io.prediction.controller.java.JavaEngine;
import io.prediction.controller.java.JavaEngineBuilder;

public class EngineFactory implements IJavaEngineFactory {
  public JavaEngine<TrainingData, EmptyParams, PreparedData, Query, Double, Object> apply() {
    return new JavaEngineBuilder<
      TrainingData, EmptyParams, PreparedData, Query, Double, Object> ()
      .dataSourceClass(DataSource.class)
      .preparatorClass(Preparator.class)
      .addAlgorithmClass("naivebayes", Algorithm.class)
      .servingClass()
      .build();
  }
}