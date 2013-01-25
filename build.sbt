name := "sorts"

version := "0.0.1"

scalaVersion := "2.10.0"

scalacOptions ++= Seq("-deprecation", "-unchecked")

resolvers ++= Seq(
  "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq(
  "com.github.axel22" %% "scalameter" % "0.2.1-SNAPSHOT" % "test",
  "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"
)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")
