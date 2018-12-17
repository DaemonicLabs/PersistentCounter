package moe.nikky.counter

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.task

open class CounterPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {

            val extension = extensions.create("counter", CounterExtension::class.java, project)

            project.afterEvaluate {
                extension.variables.forEach {
                    val sometask = task<CounterTask>(it.id) {
                        variable = it
                    }
                    val increaseTask = task<CounterTask>(it.id + "Increase") {
                        variable = it
                        action = CounterAction.INCREASE
                    }
                    val decreaseTask = task<CounterTask>(it.id + "Decrease") {
                        variable = it
                        action = CounterAction.DECREASE
                    }
                    val resetTask = task<CounterTask>(it.id + "Reset") {
                        variable = it
                        action = CounterAction.SET
                        value = variable.default.toString()
                    }
                }
            }
        }
    }
}