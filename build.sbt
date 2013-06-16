name := "sorts"

version := "0.0.1"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation", "-unchecked")

javaOptions in run += "-preJDK7"

resolvers ++= Seq(
  "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq(
  "com.github.axel22" % "scalameter_2.10" % "0.4-M1" % "test",
  "org.scalacheck" % "scalacheck_2.10.0" % "1.10.0" % "test"
)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")
