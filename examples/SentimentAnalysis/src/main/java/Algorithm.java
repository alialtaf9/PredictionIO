package io.prediction.examples.java.SentimentAnalysis;

import io.prediction.controller.java.LJavaAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;

public class Algorithm extends LJavaAlgorithm<PreparedData, Model, Query, Double> {
  public final AlgoParams params;
  final static Logger logger = LoggerFactory.getLogger(Algorithm.class);

  public Algorithm(AlgoParams params) {
    this.params = params;
  }

  public Model train(PreparedData data) {
    NaiveBayesModel model = NaiveBayes.train(data.phraseVectors);
    return new Model(model, data.wordMappings, data.idfs);
  }

  public Double predict(Model model, Query query) {
    final String phrase = query.phrase;
    Map<String, Double> wordCounts = new TreeMap<String, Double>();
    String[] words = phrase.split(" ");
    double increment = 1.0/words.length;
    for (String word : words) {
      int wordMapping = model.wordMappings.get(word);
      if (wordCounts.containsKey(wordMapping)) {
        wordCounts.put(word, wordCounts.get(word) + increment);
      }
      else {
      }
    }
    int[] wordIndex = wordCounts.keySet().toArray(new int[0]);
    double[] termCount = wordCounts.values().toArray(new double[0]);
    Vector v = Vectors.sparse(model.wordMappings.size(), wordIndex, termCount);
    return model.model.predict(v);
  }
}

