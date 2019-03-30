package moe.nikky.counter

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.task

open class CounterPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            Counter.variables.forEach {
                with(it.project) {
                    logger.info("processing variable: $it")
                    (it.id).let { taskname ->
                        if (tasks.names.contains(taskname)) {
                            val sometask = it.project.task<CounterTask>(taskname) {
                                variable = it
                            }
                        } else {
                            logger.debug("'$taskname' already exists")
                        }
                    }

                    (it.id + "Increment").let { taskname ->
                        if(tasks.names.contains(taskname)) {
                            val increaseTask = it.project.task<CounterTask>(taskname) {
                                variable = it
                                action = CounterAction.INCREMENT
                            }
                        } else {
                            logger.debug("'$taskname' already exists")
                        }
                    }

                    (it.id + "Decrement").let { taskname ->
                        if(tasks.names.contains(taskname)) {
                            val increaseTask = it.project.task<CounterTask>(taskname) {
                                variable = it
                                action = CounterAction.DECREMENT
                            }
                        } else {
                            logger.debug("'$taskname' already exists")
                        }
                    }

                    (it.id + "Set").let { taskname ->
                        if(tasks.names.contains(taskname)) {
                            val increaseTask = it.project.task<CounterTask>(taskname) {
                                variable = it
                                action = CounterAction.SET
                            }
                        } else {
                            logger.debug("'$taskname' already exists")
                        }
                    }

                    (it.id + "Reset").let { taskname ->
                        if(tasks.names.contains(taskname)) {
                            val increaseTask = it.project.task<CounterTask>(taskname) {
                                variable = it
                                action = CounterAction.SET
                                value = variable.default.toString()
                            }
                        } else {
                            logger.debug("'$taskname' already exists")
                        }
                    }
                }
            }
        }
    }
}