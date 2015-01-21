package io.prediction.examples.java.SentimentAnalysis;

import java.io.Serializable;

public class Query implements Serializable {
  public String phrase;

  public Query(String phrase) {
    this.phrase = phrase;
  }
}