PersistentCounter

usage example:

```kotlin
val major = Constants.major
val minor = Constants.minor
val patch = Constants.patch

configure<CounterExtension> {
    variable(id = "buildnumber", key = "$major.$minor.$patch")
}

val counter: CounterExtension = extensions.getByType()

val buildnumber by counter.map

version = "$major.$minor.$patch-$buildnumber"

```