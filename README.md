# PersistentCounter

## Gradle Plugin

https://plugins.gradle.org/plugin/moe.nikky.persistentCounter

## Usage Example:

kotlin-dsl:
```kotlin
val major = Constants.major
val minor = Constants.minor
val patch = Constants.patch

counter {
    variable(id = "buildnumber", key = "$major.$minor.$patch")
}

val counter: CounterExtension = extensions.getByType()

val buildnumber by counter.map

version = "$major.$minor.$patch-$buildnumber"
```

groovy (wip): 
```groovy
counter {
    variable(id = "buildnumber", key = "$major.$minor.$patch")
}

def counter = extensions.getByType<CounterExtension>()

def buildnumber = counter.map.buildnumber

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

val buildnumber by counter.map
val otherCounter = counter.get("otherCounter")
```