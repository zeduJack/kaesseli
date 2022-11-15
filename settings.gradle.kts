pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

include("shared","backend","mobile", "mobile:common", "mobile:android")
/*
include(":shared")

include(":mobile:common")
include(":mobile:android")



include(":mobile")
include(":backend")
 */
/*
include("shared")
include("mobile")
include("backend")
 */

/*
Vielleicht würde eine der folgenden Varianten besser funktionieren.
- 2 multiprojects und dann in beiden so was:
include(":shared")
project(":shared").projectDir = file("../shared")

- Shared als eigenes Projekt und dann so was:
includeBuild()
 */