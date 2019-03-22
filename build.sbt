organization += "me.zalo.oa"

name := "oa-api"

version := "0.6"

scalaVersion := "2.12.8"

crossScalaVersions := Seq("2.11.12", "2.12.8")

val circeVersion = "0.10.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-generic-extras",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.4.1"
)
