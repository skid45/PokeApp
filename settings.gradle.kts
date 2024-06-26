pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "PokeApp"
include(":app")
include(":base:core")
include(":base:coreui")
include(":base:network")
include(":base:utils")
include(":api:pokemon_list_api")
include(":feature:pokemon_list")
include(":api:pokemon_details_api")
include(":feature:pokemon_details")
