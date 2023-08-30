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
rootProject.name = "Notes"
include(":app")
include(":core:ui")
include(":core:common")
include(":core:navigation")
include(":feature:notes-list")
include(":api:notes")
