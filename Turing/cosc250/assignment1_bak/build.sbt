lazy val root = (project in file(".")).
  settings(
    name := "assignment1",
    version := "2020.1",
    scalaVersion := "2.13.1"
  )

libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"
