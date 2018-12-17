package moe.nikky.counter

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

open class CounterTask : DefaultTask() {
    init {
        group = "counter"
        description = "Track Variables"
    }

    @Input
    lateinit var variable: Variable

    @Input
    var action: CounterAction? = null
        @Option(option = "action", description = "Increase, Decrease or Set the value")
        set

    @Input
    var value: String = ""
        @Option(option = "setValue", description = "Sets the exact value")
        set

    @TaskAction
    fun exec() {
        logger.debug("action: $action")
        logger.debug("value: $value")
        logger.debug("variable: $variable")

        when (action) {
            CounterAction.INCREASE -> {
                var value = variable.value
                value += 1
                variable.value = value
            }
            CounterAction.DECREASE -> {
                var value = variable.value
                value -= 1
                value = Math.max(0, value)
                variable.value = value
            }
            CounterAction.SET -> {
                value.takeIf {
                    it.isNotBlank()
                }?.run {
                    toIntOrNull()
                }?.let { value ->
                    variable.value = value
                } ?: run {
                    logger.lifecycle("value is not set")
                    return
                }
            }
            null -> {
                logger.lifecycle("action is not set")
                return
            }
        }
    }
}