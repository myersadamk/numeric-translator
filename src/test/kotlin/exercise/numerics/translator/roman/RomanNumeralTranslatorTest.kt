package exercise.numerics.translator.roman

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.EnumSource
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private val translator: RomanNumeralTranslator = RomanNumeralTranslator()

class RomanNumeralTranslatorTest {

    @Test
    fun `Rejects zero`() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            message = "0 is not supported by the roman numeral system",
            block = { translator.toText(0) }
        )
    }

    @Test
    fun `Rejects negative values`() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            message = "Negative numbers are not supported by the roman numeral system",
            block = { translator.toText(-1) }
        )
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
}

class TranslationFromRomanNumeralTest {

    @Test
    fun `Rejects blank strings`() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            message = "Textual representation cannot be empty or blank",
            block = { translator.fromText("") }
        )
    }
}
