package exercise.numerics.translator

import org.springframework.shell.command.CommandContext

data class SimpleTranslatorCommand<T>(
    val key: String,
    val numericType: Class<T>,
    val argumentName: String = "value",
    private val translator: NumericTranslator<T>
) {
    fun translateTo(context: CommandContext) {
        writeResultOrError(context) {
            translator.toText(context.getOptionValue(argumentName))
        }
    }

    fun translateFrom(context: CommandContext) {
        writeResultOrError(context) {
            translator.fromText(context.getOptionValue(argumentName))
        }
    }

    private fun <T> writeResultOrError(context: CommandContext, translationFunction: () -> T) {
        val result = runCatching {
            translationFunction()
        }.getOrElse { error ->
            error.message ?: "Unknown error: $error"
        }
        context.terminal.writer().println(result)
    }
}