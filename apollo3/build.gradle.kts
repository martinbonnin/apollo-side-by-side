plugins {
  id("org.jetbrains.kotlin.jvm")
  id("com.apollographql.apollo3")
}

apollo {
  packageName.set("com.example")
  schemaFile.set(rootProject.file("app/src/main/graphql/schema.graphqls"))
}

dependencies {
  api("com.apollographql.apollo3:apollo-api")
}