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
val circeVersion = "0.9.1"

libraryDependencies ++=
  Seq(
    "org.typelevel" %% "cats-core" % "1.0.1",
    "com.chuusai" %% "shapeless" % "2.3.10",
    "io.github.mkotsur" %% "aws-lambda-scala" % "0.0.10",
    "com.thoughtworks.xstream" % "xstream" % "1.4.19",
    "org.codehaus.jettison" % "jettison" % "1.3.8",
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,

    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  )

mainClass in assembly := Some("onextent.xml.Main")
assemblyJarName in assembly := "NaviXml.jar"

assemblyMergeStrategy in assembly := {
  case PathList("reference.conf") => MergeStrategy.concat
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case PathList("META-INF", _ @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

