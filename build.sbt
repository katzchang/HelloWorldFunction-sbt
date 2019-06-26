organization := "com.voyagegroup"

name := "hello-sam"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
scalacOptions := Seq("-target:jvm-1.8")

//initialize := {
//  val _ = initialize.value
//  if (sys.props("java.specification.version") != "1.8")
//    sys.error("Java 8 is required for this project.")
//}

resolvers += Classpaths.typesafeReleases

val aws_lambda_java_core = "com.amazonaws" % "aws-lambda-java-core" % "1.2.0"
val junit = "junit" % "junit" % "4.12" % "test"

libraryDependencies ++= Seq(
  aws_lambda_java_core,

  junit
)

