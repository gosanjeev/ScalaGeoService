addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.5")

logLevel := Level.Warn

resolvers +=  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.url("sbt-plugin-releases",
  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)
