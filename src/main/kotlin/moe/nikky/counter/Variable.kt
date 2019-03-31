package moe.nikky.counter

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import java.io.File

data class Variable(
    internal val project: Project,
    internal val id: String,
    internal val key: String
) {
    var default = 1

    private val folder: File
        get() {
            val extension: CounterExtension = project.extensions.getByType()
            return extension.shareHome.resolve(project.name + project.path.replace(':', '/'))
                .resolve(id)
        }

    private val file: File = folder.resolve("$key.txt")

    internal var value: Int
        get() {
            val file = file
            file.parentFile.mkdirs()
            project.logger.info("loading from file: $file")
            if (!file.exists()) {
                file.writeText(default.toString())
                return default
            }
            return file.readText().toInt()
        }
        set(value) {
            val file = file
            file.parentFile.mkdirs()
            file.writeText(value.toString())
            project.logger.lifecycle("file: $file")
        }
}