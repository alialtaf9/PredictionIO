package io.prediction.examples.java.SentimentAnalysis;

import io.prediction.controller.java.LJavaPreparator;
import io.prediction.controller.java.EmptyParams;

import java.lang.Math;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.linalg.Vectors;

public class Preparator extends LJavaPreparator<TrainingData, PreparedData> {
  public Preparator() {

  }

  public PreparedData prepare(TrainingData trainingData) {
    Map<String, Double> labeledPhrases = trainingData.labeledPhrases;
    Map<String, Integer> wordMappings = new HashMap<String, Integer>();
    Map<String, Double> wordIDFs = new HashMap<String, Double>();
    List<LabeledPoint> training = new ArrayList<LabeledPoint>();
    int index = 0;

    for (String phrase : labeledPhrases.keySet()) {
      boolean inDocument = false;
      for (String word : phrase.split(" ")) {
        if (!wordMappings.containsKey(word)) {
          wordMappings.put(word, index);
          index++;
          if (!inDocument) {
            if (wordIDFs.containsKey(word)) wordIDFs.put(word, wordIDFs.get(word)+1);
            else wordIDFs.put(word, 1.0);
            inDocument = true;
          }
        }
      }
    }

    int numPhrases = labeledPhrases.size();
    for (String word : wordIDFs.keySet()) {
      double count = wordIDFs.get(word);
      count = Math.log(count/numPhrases);
    }

    for (String phrase : labeledPhrases.keySet()) {
      double val = labeledPhrases.get(phrase);
      Map<Integer, Double> wordCounts = new TreeMap<Integer, Double>();
      String[] words = phrase.split(" ");
      double increment = 1.0/words.length;
      for (String word : words) {
        int wordMapping = wordMappings.get(word);
        if (wordCounts.containsKey(wordMapping)) {
          wordCounts.put(wordMapping, wordCounts.get(word) + increment);
        }
        else {
          wordCounts.put(wordMapping, increment);
        }
      }
      int[] wordIndex = wordCounts.keySet().toArray(new int[0]);
      double[] termCount = wordCounts.values().toArray(new double[0]);
      LabeledPoint lp = new LabeledPoint(labeledPhrases.get(phrase), Vectors.sparse(index, wordIndex, termCount));
      training.add(lp);
    }

    return new PreparedData(training, wordMappings, wordIDFs);
  }
}