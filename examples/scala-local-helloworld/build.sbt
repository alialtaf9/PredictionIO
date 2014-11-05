import AssemblyKeys._

assemblySettings

name := "example-scala-local-helloworld"

organization := "org.sample"

libraryDependencies ++= Seq(
  "io.prediction" %% "core" % "0.8.1" % "provided",
  "io.prediction" %% "data" % "0.8.1" % "provided",
  "org.apache.spark" %% "spark-core" % "1.1.0" % "provided")
