import sbt._
import sbt.Keys._

object Resolvers {
  val myResolvers = Seq(
    "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases/",
    "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"
  )
}

object Versions {
  val guava = "12.0"
}

object Dependencies {
  import Resolvers._
  import Versions._

  val rainbow      = "pl.project13.scala" %% "rainbow"        % "0.1"
  val guava        = "com.google.guava"    % "guava"          % Versions.guava

  // testing
  val scalaTest = "org.scalatest"    %% "scalatest"      % "1.7.RC1" % "test"
  val mockito   = "org.mockito"       % "mockito-core"   % "1.8.5" % "test"

}

object BuildSettings {
  import Resolvers._
  import Dependencies._

  val generalDependencies = Seq(
    rainbow, guava,
    scalaTest, mockito
  )
  
  val buildSettings = Defaults.defaultSettings ++
    Seq(
      organization := "com.sckrk",
      name         := "legacy-code-retreat",
      scalaVersion := "2.9.1",
      libraryDependencies ++= generalDependencies
    )

}

object CodeRetreatBuild extends Build {
  import Dependencies._
  import BuildSettings._

  lazy val root = Project (
    "legacy-code-retreat",
    file("."),
    settings = buildSettings ++ Seq (
        libraryDependencies ++= generalDependencies ++ Seq()
      )
  )

}
