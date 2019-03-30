# PersistentCounter

this plugin counts numbers up, those numbers are persisted between builds
and saved using a combination of the project, value name and value key
if any of those changes the number resets to the default (`1`)
so by using your version as a key, you get a buildnumber that resets on a version bump

## Gradle Plugin

https://plugins.gradle.org/plugin/moe.nikky.persistentCounter

or from `http://maven.modmuss50.me/`
```kotlin
plugins {
  id("mode.nikky.persistentCounter") version "0.0.8-SNAPSHOT"
}
```

## Tasks

for each variable it will register the tasks:

- `${variable}`
- `${variable}Increase`
- `${variable}Decrease`
- `${variable}Set` argument example: `--setValue=7`
- `${variable}Reset`

## Usage Example:

kotlin-dsl:
```kotlin
import moe.nikky.counter.counterVariable

val buildnumber = counterVariable(id = "buildnumber", key = "$major.$minor.$patch")

version = "$major.$minor.$patch-$buildnumber"
```

groovy (wip): 
```groovy
import moe.nikky.counter.counterVariable

val buildnumber = counterVariable(id = "buildnumber", key = "$major.$minor.$patch")

version = "$major.$minor.$patch-$buildnumber"
```

## Full Example

```kotlin
val major = Constants.major
val minor = Constants.minor
val patch = Constants.patch

counter {
    variable(id = "buildnumber", key = "$major.$minor.$patch") {
      default = 1
    }
    variable(id = "otherCounter", key = "$someProperty") {
      default = 0
    }
}

val counter: CounterExtension = extensions.getByType()

val buildnumber = counterVariable(id = "buildnumber", key = "$major.$minor.$patch")
val otherCounter = counterVariable(id = "otherCounter", key = "$someProperty") {
  default = 0
}
```