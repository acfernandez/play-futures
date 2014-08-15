import play.Project._
 
name := "play-futures"

organization := "ar.acf.futures"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(  
  jdbc,
  anorm,
  cache
)

playScalaSettings
