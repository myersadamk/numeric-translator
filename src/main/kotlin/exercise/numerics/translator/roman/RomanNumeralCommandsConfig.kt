package exercise.numerics.translator.roman

import exercise.numerics.translator.SimpleTranslatorCommand
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.shell.command.CommandRegistration
import org.springframework.shell.context.InteractionMode

@Configuration
class RomanNumeralCommandsConfig(romanNumeralTranslator: RomanNumeralTranslator) {

    private val command = SimpleTranslatorCommand(
        key = "roman-numeral",
        numericType = Int::class.java,
        translator = romanNumeralTranslator
    )

    @Bean
    fun toRomanNumeral(translator: RomanNumeralTranslator): CommandRegistration {
        return CommandRegistration.builder()
            .interactionMode(InteractionMode.INTERACTIVE)
            .command("to-${command.key}")
            .description("Converts a number to a Roman numeral")
            .withTarget()
            .consumer(command::translateTo).and()
            .withOption()
            .description("The numeric value to convert")
            .arity(1, Int.MAX_VALUE)
            .longNames(command.argumentName)
            .shortNames(command.argumentName.first())
            .position(1)
            .required()
            .type(command.numericType)
            .and().build()
    }

    @Bean
    fun fromRomanNumeral(translator: RomanNumeralTranslator): CommandRegistration {
        return CommandRegistration.builder()
            .interactionMode(InteractionMode.INTERACTIVE)
            .command("from-${command.key}")
            .description("Converts a Roman numeral to a number")
            .withTarget()
            .consumer(command::translateFrom).and()
            .withOption()
            .description("The Roman numeral to convert")
            .longNames(command.argumentName)
            .shortNames(command.argumentName.first())
            .position(1)
            .required()
            .type(String::class.java)
            .and().build()
    }
}