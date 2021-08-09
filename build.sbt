name := "dependency-injection-in-scala"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies ++= Seq(
  "javax.inject" % "javax.inject" % "1",
  "org.scalatest" %% "scalatest" % "3.2.3" % Test,
  "org.mockito" %% "mockito-scala" % "1.5.12" % Test
)

