package moe.nikky.counter

import org.gradle.api.Project
import java.io.File

open class CounterExtension(
    private val project: Project
) {
    internal val variables = mutableListOf<Variable>()

    internal val shareHome: File = File(System.getProperty("user.home"))
        .resolve(".local")
        .resolve("share")
        .resolve("persistentCounter")

    fun variable(id: String, key: String, configure: Variable.() -> Unit = {}): Int {
        project.logger.info("registering variable '${id}-${key}' for project ${project.path}")
        val variable = Variable(
            project = project,
            id = id,
            key = key
        )
        variable.configure()
        variables += variable
        return variable.value
    }
}