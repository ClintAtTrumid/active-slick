import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform

trait BuildSettings { this:Build => 

	val ScalaVersion = "2.11.2"
	
	val Organization = "io.strongtyped"

	val ScalacOptions = Seq("-unchecked", "-deprecation", "-feature", "-Xlint")

	val projectSettings = Seq(
		  organization 		:= Organization
		, scalaVersion 		:= ScalaVersion
		, scalacOptions 	:= ScalacOptions
	) ++ SbtScalariform.scalariformSettings ++ sonatypePublishSettings

  	def sonatypePublishSettings = Seq(
	  publishMavenStyle := true,
	  publishTo := {
		val nexus = "https://oss.sonatype.org/"
		if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
		else Some("releases"  at nexus + "service/local/staging/deploy/maven2")
	  },
	  pomExtra 		:= (<url>https://github.com/strongtyped/active-slick</url>
      <licenses>
        <license>
          <name>Apache-style</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>https://github.com/strongtyped/active-slick.git</url>
        <connection>scm:git:git@github.com:strongtyped/active-slick.git</connection>
      </scm>
      <developers>
        <developer>
          <id>@renatocaval</id>
          <name>Renato Cavalcanti</name>
          <url>http://www.strongtyped.io/</url>
        </developer>
      </developers>)
	)
}