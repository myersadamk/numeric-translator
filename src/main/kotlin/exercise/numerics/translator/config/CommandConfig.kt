package exercise.numerics.translator.config

import exercise.numerics.translator.SimpleTranslatorCommand
import exercise.numerics.translator.roman.RomanNumeralTranslator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.shell.command.CommandRegistration
import org.springframework.shell.context.InteractionMode

@Configuration
class CommandConfig {

    @Bean
    fun romanNumerals(translator: RomanNumeralTranslator): CommandRegistration {
        val command = SimpleTranslatorCommand(
            key = "roman-numeral",
            numericType = Int::class.java,
            translator = translator
        )

        return CommandRegistration.builder()
            .interactionMode(InteractionMode.ALL)
            .command("to-${command.key}")
            .withTarget()
            .consumer(command::translateTo).and()
            .withOption()
            .longNames(command.argumentName)
            .required()
            .type(command.numericType)
            .and()
            .build()
    }
//    @Bean
//    fun translatorCommands(
//        builder: CommandRegistration.BuilderSupplier,
//        translators: List<TranslatorCommand<*>>
//    ): List<CommandRegistration> {
//        return translators.map { command ->
//            builder.get()
//                .command("to-${command.commandKey()}")
//                .interactionMode(InteractionMode.ALL)
//                .withTarget().function { ctx ->
//                    command.translator().fromText(ctx.rawArgs.last())
//                }.and()
//                .build()
//        }
//    }

//    @Bean
//    fun romans(command: RomanNumeralTranslatorCommand) =
//        CommandRegistration.builder()
//            .command(command.commandKey())
//            .interactionMode(InteractionMode.ALL)
//            .build()
}