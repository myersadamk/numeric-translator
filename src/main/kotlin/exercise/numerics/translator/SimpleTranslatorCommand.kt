package exercise.numerics.translator

import org.springframework.shell.command.CommandContext

data class SimpleTranslatorCommand<T>(
    val key: String,
    val numericType: Class<T>,
    val argumentName: String = "value",
    private val translator: NumericTranslator<T>
) {
    fun translateTo(context: CommandContext) {
        val result = runCatching {
            translator.toText(context.getOptionValue(argumentName))
        }.getOrElse { error ->
            error.message ?: "Unknown error: $error"
        }
        context.terminal.writer().println(result)
    }

    fun translateFrom(context: CommandContext) {
        TODO()
    }
}