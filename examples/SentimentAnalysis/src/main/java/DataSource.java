package io.prediction.examples.java.SentimentAnalysis;

import io.prediction.controller.java.LJavaDataSource;
import scala.Tuple2;
import scala.Tuple3;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Iterable;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSource extends LJavaDataSource<
  DataSourceParams, TrainingData, Query, Object> {

  final static Logger logger = LoggerFactory.getLogger(DataSource.class);

  DataSourceParams params;

  public DataSource(DataSourceParams params) {
    this.params = params;
  }

  public Iterable<Tuple3<Object, TrainingData, Iterable<Tuple2<Query, Object>>>> read() {

    File sentimentFile = new File(params.filePath);
    Scanner sc = null;

    try {
      sc = new Scanner(sentimentFile);
    } catch (FileNotFoundException e) {
      logger.error("Caught FileNotFoundException " + e.getMessage());
      System.exit(1);
    }

    Map<String, Double> sentiments = new HashMap<String, Double>();

    while (sc.hasNext()) {
      String line = sc.nextLine();
      String[] tokens = line.split("[\t,]");
      try {
        sentiments.put(tokens[2], Double.parseDouble(tokens[3]));
      } catch (Exception e) {
        logger.error("Can't parse rating file. Caught Exception: " + e.getMessage());
        System.exit(1);
      }
    }

    List<Tuple3<Object, TrainingData, Iterable<Tuple2<Query, Object>>>> data =
      new ArrayList<Tuple3<Object, TrainingData, Iterable<Tuple2<Query, Object>>>>();

    data.add(new Tuple3<Object, TrainingData, Iterable<Tuple2<Query, Object>>>(
      null,
      new TrainingData(sentiments),
      new ArrayList<Tuple2<Query, Object>>()
    ));

    return data;
  }

}
