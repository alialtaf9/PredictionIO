package io.prediction.controller.java

import io.prediction.controller.Engine
import io.prediction.controller.Params
import io.prediction.controller.EngineParams
import io.prediction.controller.IEngineFactory

import java.lang.{ Iterable => JIterable }
import java.util.{ Map => JMap }
import java.util.HashMap

import scala.collection.JavaConversions._

/**
 * This class chains up the entire data process. PredictionIO uses this
 * information to create workflows and deployments. In Java, use
 * JavaEngineBuilder to conveniently instantiate an instance of this class.
 * For now it only accepts LJavaServing as the serving class.
 *
 * @param <TD> Training Data
 * @param <DP> Data Parameters
 * @param <PD> Prepared Data
 * @param <Q> Input Query
 * @param <P> Output Prediction
 * @param <A> Actual Value
 */
class PJavaEngine[TD, DP, PD, Q, P, A](
    dataSourceClass: Class[_ <: PJavaDataSource[_ <: Params, DP, TD, Q, A]],
    preparatorClass: Class[_ <: PJavaPreparator[_ <: Params, TD, PD]],
    algorithmClassMap
      : JMap[String, Class[_ <: PJavaAlgorithm[_ <: Params, PD, _, Q, P]]],
    servingClass: Class[_ <: LJavaServing[_ <: Params, Q, P]]
) extends Engine(
    dataSourceClass,
    preparatorClass,
    Map(algorithmClassMap.toSeq: _*),
    servingClass)
