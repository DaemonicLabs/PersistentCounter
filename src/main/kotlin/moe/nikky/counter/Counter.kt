package moe.nikky.counter

import org.gradle.api.Project
import java.io.File

object Counter {
    val variables = mutableListOf<Variable>()

    val shareHome: File = File(System.getProperty("user.home"))
        .resolve(".local")
        .resolve("share")
        .resolve("persistentCounter")
}
fun Project.counterVariable(id: String, key: String, configure: Variable.() -> Unit = {}): Int {
    project.logger.lifecycle("registering variable '${id}-${key}' for project ${project.path}")
    val variable = Variable(
        project = this,
        id = id,
        key = key
    )
    variable.configure()
    Counter.variables += variable
    return variable.value
}