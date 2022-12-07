name := "NaviXml"

fork := true
javaOptions in test ++= Seq(
  "-Xms128M", "-Xmx256M",
  "-XX:MaxPermSize=256M",
  "-XX:+CMSClassUnloadingEnabled"
)

parallelExecution in test := false

version := "1.0"

scalaVersion := "2.12.4"
val circeVersion = "0.14.3"

libraryDependencies ++=
  Seq(
    "org.typelevel" %% "cats-core" % "2.9.0",
    "com.chuusai" %% "shapeless" % "2.3.10",
    "io.github.mkotsur" %% "aws-lambda-scala" % "0.3.0",
    "com.thoughtworks.xstream" % "xstream" % "1.4.19",
    "org.codehaus.jettison" % "jettison" % "1.5.3",
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,

    "org.scalatest" %% "scalatest" % "3.2.14" % "test"
  )

mainClass in assembly := Some("onextent.xml.Main")
assemblyJarName in assembly := "NaviXml.jar"

assemblyMergeStrategy in assembly := {
  case PathList("reference.conf") => MergeStrategy.concat
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case PathList("META-INF", _ @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

