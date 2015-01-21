package io.prediction.examples.java.SentimentAnalysis;

import io.prediction.controller.java.JavaParams;

public class AlgoParams implements JavaParams {
  public double regularization;

  public AlgoParams(double regularization) {
    this.regularization = regularization;
  }
}