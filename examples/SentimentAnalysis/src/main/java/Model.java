package io.prediction.examples.java.SentimentAnalysis;

import java.io.Serializable;
import java.util.Map;

import org.apache.spark.mllib.classification.NaiveBayesModel;

public class Model implements Serializable {
  public NaiveBayesModel model;
  public Map<String, Integer> wordMappings;
  public Map<String, Double> wordIDFs;

  public Model(NaiveBayesModel model, Map<String, Integer> wordMappings, Map<String, Double> wordIDFs) {
    this.model = model;
    this.wordMappings = wordMappings;
    this.wordIDFs = wordIDFs;
  }
}