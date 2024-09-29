import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.mavenPublish)
}

android {
    compileSdk = 34
    namespace = "bz.tsung.kmp.objectify"

    defaultConfig {
        minSdk = 19
        lint {
            targetSdk = 34
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
    jvm()
    androidTarget {
        publishLibraryVariants("release", "debug")
        publishLibraryVariantsGroupedByFlavor = true
    }

    sourceSets {
        commonMain {
            dependencies {
                api(libs.gson)
                api(libs.datastore.preferences)
            }
        }
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.DEFAULT)
    signAllPublications()
}
