import ReleaseTransformations._

organization := "org.konstructs"

name := "konstructs-server-api"

scalaVersion := "2.10.4"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

crossPaths := false

autoScalaLibrary := false

val akkaVersion = "2.2.4"

javacOptions ++= Seq( "-Xlint:deprecation" )

libraryDependencies ++= Seq(
  "com.typesafe.akka"      %% "akka-actor"    % akkaVersion,
  "com.google.code.gson"   %  "gson"          % "2.6.2"
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
