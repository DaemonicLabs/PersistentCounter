package moe.nikky.counter

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.task

open class CounterPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            Counter.variables.forEach {
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
                val setTask = task<CounterTask>(it.id + "Set") {
                    variable = it
                    action = CounterAction.SET
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