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
    //repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri( "https://repo.spring.io/release")
        }
        maven {
            url = uri ("https://repository.jboss.org/maven2")
        }
        //jcenter() // Warning: this repository is going to shut down soon
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "J-G-Campus-test"
include(":app")
 