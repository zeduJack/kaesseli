pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

include(":mobile")
include(":mobile:android")
include(":mobile:common")
include(":backend")
include(":shared")

/*
Vielleicht würde eine der folgenden Varianten besser funktionieren.
- 2 multiprojects und dann in beiden so was:
include(":shared")
project(":shared").projectDir = file("../shared")

- Shared als eigenes Projekt und dann so was:
includeBuild()
 */