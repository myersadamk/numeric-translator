package exercise.numerics.translator.config

import exercise.numerics.translator.SimpleTranslatorCommand
import exercise.numerics.translator.roman.RomanNumeralTranslator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.shell.command.CommandRegistration
import org.springframework.shell.context.InteractionMode

@Configuration
class CommandConfig(romanNumeralTranslator: RomanNumeralTranslator) {

    private val command = SimpleTranslatorCommand(
        key = "roman-numeral",
        numericType = Int::class.java,
        translator = romanNumeralTranslator
    )

    @Bean
    fun romanNumerals(translator: RomanNumeralTranslator): CommandRegistration {
        return CommandRegistration.builder().interactionMode(InteractionMode.ALL)
                .command("to-${command.key}")
                .withTarget()
                .consumer(command::translateTo).and()
                .withOption()
                .longNames(command.argumentName)
                .required()
                .type(command.numericType)
                .and().build()
    }

    @Bean
    fun fromRomanNumeral(translator: RomanNumeralTranslator): CommandRegistration {
        return CommandRegistration.builder().interactionMode(InteractionMode.ALL)
                .command("from-${command.key}")
                .withTarget()
                .consumer(command::translateFrom).and()
                .withOption()
                .longNames(command.argumentName)
                .required()
                .type(String::class.java)
                .and().build()
    }
}