package io.prediction.examples.java.SentimentAnalysis;

import java.io.Serializable;
import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.regression.LabeledPoint;

public class PreparedData extends TrainingData {
  public JavaRDD<LabeledPoint> phraseVectors;
  public Map<String, Double> idfs;
  public Map<String, Integer> wordMappings;

  public PreparedData(JavaRDD<LabeledPoint> phraseVectors, Map<String, Double> idfs, Map<String, Integer> wordMappings) {
    super(ids);
    this.phraseVectors = phraseVectors;
    this.idfs = idfs;
    this.wordMappings = wordMappings;
  }

}