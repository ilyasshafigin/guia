buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.android.tools.build:gradle:7.4.1")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.24.0")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.6.10")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.21.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
