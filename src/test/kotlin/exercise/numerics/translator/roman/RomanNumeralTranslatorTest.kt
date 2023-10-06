package exercise.numerics.translator.roman

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private val translator: RomanNumeralTranslator = RomanNumeralTranslator()

class TranslationToRomanNumeralTest {

    @Test
    fun `Rejects zero`() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = { translator.toText(0) }
        ).run {
            assertEquals("0 is not supported by the roman numeral system", message)
        }
    }

    @Test
    fun `Rejects negative values`() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = { translator.toText(-1) }
        ).run {
            assertEquals(
                "Negative numbers are not supported by the roman numeral system",
                message
            )
        }
    }

    @ParameterizedTest
    @EnumSource(RomanNumeral::class)
    fun `Translates evenly-divisible Roman Numerals`(romanNumeral: RomanNumeral) {
        assertEquals(romanNumeral.symbol, translator.toText(romanNumeral.value))
    }

    @ParameterizedTest
    @ArgumentsSource(NonEvenlyDivisibleScenarios::class)
    fun `Translates non evenly-divisible Roman Numerals`(value: Int, translation: String) {
        assertEquals(translation, translator.toText(value))
    }
}

/**
 * Note: in JUnit, I sometimes use @Nested to group tests containing similar functionality. Since
 * Kotlin can have multiple public classes in a file, it seems reasonable to split them out instead.
 */
class TranslationFromRomanNumeralTest {

    @ParameterizedTest
    @ValueSource(strings = ["", " "])
    fun `Rejects blank strings`(text: String) {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = { translator.fromText(text) }
        ).run {
            assertEquals(
                "Textual representation of a roman numeral cannot be empty or blank", message
            )
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["O", "X?", "I1", "X IV"])
    fun `Rejects invalid characters`(text: String) {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = { translator.fromText(text) },
        ).run {
            assertEquals("Text must contain only valid roman numerals", message)
        }
    }

    @ParameterizedTest
    @EnumSource(RomanNumeral::class)
    fun `Translates evenly-divisible Roman Numerals`(romanNumeral: RomanNumeral) {
        assertEquals(romanNumeral.value, translator.fromText(romanNumeral.symbol))
    }


    @ParameterizedTest
    @ArgumentsSource(NonEvenlyDivisibleScenarios::class)
    fun `Translates non evenly-divisible Roman Numerals`(value: Int, translation: String) {
        assertEquals(value, translator.fromText(translation))
    }
}

class NonEvenlyDivisibleScenarios : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
        return Stream.of(
            Arguments.of(6, "VI"),
            Arguments.of(11, "XI"),
            Arguments.of(15, "XV"),
            Arguments.of(42, "XLII"),
            Arguments.of(55, "LV"),
            Arguments.of(93, "XCIII"),
            Arguments.of(104, "CIV"),
            Arguments.of(444, "CDXLIV"),
            Arguments.of(594, "DXCIV"),
            Arguments.of(949, "CMXLIX"),
            Arguments.of(1994, "MCMXCIV"),
            Arguments.of(3945, "MMMCMXLV"),
        )
    }
}
