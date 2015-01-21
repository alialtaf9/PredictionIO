package io.prediction.examples.java.SentimentAnalysis;

import java.io.Serializable;
import java.util.Map;

public class TrainingData implements Serializable {
  public Map<String, Double> labeledPhrases;

  public TrainingData(Map<String, Double> labeledPhrases) {
    this.labeledPhrases = labeledPhrases;
  }
}