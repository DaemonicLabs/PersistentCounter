package moe.nikky.counter

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.task

open class CounterPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            Counter.variables.forEach {
                logger.info("processing variable: $it")
                val sometask = it.project.task<CounterTask>(it.id) {
                    variable = it
                }
                val increaseTask = it.project.task<CounterTask>(it.id + "Increase") {
                    variable = it
                    action = CounterAction.INCREASE
                }
                val decreaseTask = it.project.task<CounterTask>(it.id + "Decrease") {
                    variable = it
                    action = CounterAction.DECREASE
                }
                val setTask = it.project.task<CounterTask>(it.id + "Set") {
                    variable = it
                    action = CounterAction.SET
                }
                val resetTask = it.project.task<CounterTask>(it.id + "Reset") {
                    variable = it
                    action = CounterAction.SET
                    value = variable.default.toString()
                }
            }
        }
    }
}