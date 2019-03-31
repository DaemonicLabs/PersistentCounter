package moe.nikky.counter

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.task

open class CounterPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<CounterExtension>("counter", project)
        project.afterEvaluate {
            extension.variables.forEach { v ->
                with(v.project) {
                    logger.info("processing variable: $v")
                    (v.id).let { taskname ->
                        val sometask = v.project.task<CounterTask>(taskname) {
                            variable = v
                        }
                    }

                    (v.id + "Increment").let { taskname ->
                        val increaseTask = v.project.task<CounterTask>(taskname) {
                            variable = v
                            action = CounterAction.INCREMENT
                        }
                    }

                    (v.id + "Decrement").let { taskname ->
                        val increaseTask = v.project.task<CounterTask>(taskname) {
                            variable = v
                            action = CounterAction.DECREMENT
                        }
                    }

                    (v.id + "Set").let { taskname ->
                        val increaseTask = v.project.task<CounterTask>(taskname) {
                            variable = v
                            action = CounterAction.SET
                        }
                    }

                    (v.id + "Reset").let { taskname ->
                        val increaseTask = v.project.task<CounterTask>(taskname) {
                            variable = v
                            action = CounterAction.SET
                            value = variable.default.toString()
                        }
                    }
                }
            }
        }
    }
}