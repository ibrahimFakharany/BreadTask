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
    }
}

rootProject.name = "BreadTask"
include(":app")
include(":Remote")
include(":Features")
include(":Features:Home")
include(":Base")
include(":Local")
include(":Data")
include(":Domain")
include(":Model")
include(":Features:PostDetails")
