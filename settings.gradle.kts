pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
       maven { url = uri("https://www.jitpack.io") }
        jcenter()   // Warning: this repository is going to shut down soon
    }
}


rootProject.name = "GaPharma"
include(":app")
 