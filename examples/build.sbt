import AssemblyKeys._

assemblySettings

name := "examples"

organization := "io.prediction"

libraryDependencies ++= Seq(
  "io.prediction"     %% "core"           % "0.8.1" % "provided",
  "io.prediction"     %% "engines"        % "0.8.1" % "provided",
  "com.github.scopt"  %% "scopt"          % "3.2.0",
  "commons-io"         % "commons-io"     % "2.4",
  "org.apache.commons" % "commons-math3"  % "3.3",
  "org.apache.mahout"  % "mahout-core"    % "0.9",
  "org.apache.spark"  %% "spark-core"     % "1.1.0" % "provided",
  "org.apache.spark"  %% "spark-mllib"    % "1.1.0"
    exclude("org.apache.spark", "spark-core_2.10")
    exclude("org.eclipse.jetty", "jetty-server"),
  "org.clapper"       %% "grizzled-slf4j" % "1.0.2",
  "org.json4s"        %% "json4s-native"  % "3.2.10",
  "org.scala-saddle"  %% "saddle-core"    % "1.3.2"
    exclude("ch.qos.logback", "logback-classic"),
  "org.scalanlp"      %% "breeze"         % "0.9",
  "org.scalanlp"      %% "breeze-natives" % "0.9",
  "org.scalanlp"      %% "nak"            % "1.3",
  "org.scalatest"     %% "scalatest"      % "2.2.0" % "test")

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("scala", xs @ _*) => MergeStrategy.discard
    case PathList("org", "xmlpull", xs @ _*) => MergeStrategy.last
    case x => old(x)
  }
}
  

run in Compile <<= Defaults.runTask(
  fullClasspath in Compile,
  mainClass in (Compile, run),
  runner in (Compile, run))

runMain in Compile <<= Defaults.runMainTask(
  fullClasspath in Compile,
  runner in (Compile, run))

lazy val root = (project in file(".")).enablePlugins(SbtTwirl)
