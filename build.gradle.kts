// First, apply the publishing plugin
plugins {
    id("com.gradle.plugin-publish") version "0.10.0"
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    // Apply other plugins here, e.g. the kotlin plugin for a plugin written in Kotlin
    // or the groovy plugin if the plugin uses Groovy
}

val isSnapshot = !project.hasProperty("release")

group = "moe.nikky"
version = "0.0.7" + if (isSnapshot) "-SNAPSHOT" else ""

// If your plugin has any external java dependencies, Gradle will attempt to
// download them from JCenter for anyone using the plugins DSL
// so you should probably use JCenter for dependency resolution in your own
// project.
repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
}

// Use java-gradle-plugin to generate plugin descriptors and specify plugin ids
gradlePlugin {
    plugins {
        create("persistentCounterPlugin") {
            id = "moe.nikky.persistentCounter"
            implementationClass = "moe.nikky.counter.CounterPlugin"
        }
    }
}

pluginBundle {
    // These settings are set for the whole plugin bundle
    website = "https://github.com/DaemonicLabs/PersistentCounter"
    vcsUrl = "https://github.com/DaemonicLabs/PersistentCounter"

    // tags and description can be set for the whole bundle here, but can also
    // be set / overridden in the config for specific plugins
//    description = "Greetings from here!"

    // The plugins block can contain multiple plugin entries.
    //
    // The name for each plugin block below (greetingsPlugin, goodbyePlugin)
    // does not affect the plugin configuration, but they need to be unique
    // for each plugin.

    // Plugin config blocks can set the id, displayName, version, description
    // and tags for each plugin.

    // id and displayName are mandatory.
    // If no version is set, the project version will be used.
    // If no tags or description are set, the tags or description from the
    // pluginBundle block will be used, but they must be set in one of the
    // two places.

    (plugins) {

        // first plugin
        "persistentCounterPlugin" {
            // id is captured from java-gradle-plugin configuration
            displayName = "Persistent Counter"
            description = "Keeps track of buildnumbers and the like persistently"
            tags = listOf("buildnumber", "persistent", "counter")
        }

//        // another plugin
//        "goodbyePlugin" {
//            // id is captured from java-gradle-plugin configuration
//            displayName = "Gradle Goodbye plugin"
//            description = "Override description for this plugin"
//            tags = listOf("different", "for", "this", "one")
//            version = "1.3"
//        }
    }

    // Optional overrides for Maven coordinates.
    // If you have an existing plugin deployed to Bintray and would like to keep
    // your existing group ID and artifact ID for continuity, you can specify
    // them here.
    //
    // As publishing to a custom group requires manual approval by the Gradle
    // team for security reasons, we recommend not overriding the group ID unless
    // you have an existing group ID that you wish to keep. If not overridden,
    // plugins will be published automatically without a manual approval process.
    //
    // You can also override the version of the deployed artifact here, though it
    // defaults to the project version, which would normally be sufficient.

//    mavenCoordinates {
//        groupId = project.group
//        artifactId = "persistentCounter"
//        version = project.version
//    }
}