package io.prediction.examples.java.SentimentAnalysis;

import io.prediction.controller.java.JavaParams;

public class DataSourceParams implements JavaParams {
  public String filePath; // file path

  public DataSourceParams(String path) {
    this.filePath = path;
  }
}