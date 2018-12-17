package moe.nikky.counter

import org.gradle.api.Project
import java.io.File

open class CounterExtension(
    private val project: Project
) {
    internal val variables = mutableListOf<Variable>()

    val shareHome: File = File(System.getProperty("user.home"))
        .resolve(".local")
        .resolve("share")
        .resolve("persistentCounter")

    fun variable(id: String, key: String, configure: Variable.() -> Unit = {}) {
        val variable = Variable(
            project = project,
            id = id,
            key = key
        )
        variable.configure()
        variables += variable
    }

    val map: Map<String, Int>
        get() = variables.associate {
            it.id to it.value
        }

    fun get(id: String): Int {
        return variables.find {
            it.id == id
        }!!.value
    }
}

