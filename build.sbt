import Dependencies.{Versions => _, _}

lazy val hangman = project
  .settings(
    name := "hangman",
    scalaVersion := Versions.scala3,
    libraryDependencies ++= Seq(scalaTest, scalastic)
  )
