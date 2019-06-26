organization := "jp.katzchang"

name := "hello-sam"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
scalacOptions := Seq("-target:jvm-1.8")

resolvers += Classpaths.typesafeReleases

libraryDependencies += "com.amazonaws" % "aws-lambda-java-core" % "1.2.0"
libraryDependencies += "com.amazonaws" % "aws-lambda-java-events" % "2.2.6"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

