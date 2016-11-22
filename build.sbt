import ReleaseTransformations._

organization := "org.konstructs"

name := "konstructs-server-api"

scalaVersion := "2.12.1"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

crossPaths := false

autoScalaLibrary := false

val akkaVersion = "2.4.14"

javacOptions in (Compile, compile) ++= Seq( "-Xlint:deprecation", "-Xlint:unchecked" )

libraryDependencies ++= Seq(
  "com.typesafe.akka"      %% "akka-actor"    % akkaVersion,
  "com.google.code.gson"   %  "gson"          % "2.6.2",
  "org.scalatest"          %% "scalatest"     % "3.0.1"  % "test"
)

bintrayOrganization := Some("konstructs")

fork in run := true

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion,
  pushChanges
)
