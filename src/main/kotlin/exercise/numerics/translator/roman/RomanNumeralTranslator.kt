package exercise.numerics.translator.roman

import exercise.numerics.translator.NumericTranslator
import exercise.numerics.translator.roman.TranslationError.*
import org.springframework.stereotype.Service

@Service
class RomanNumeralTranslator : NumericTranslator<Int> {

    /**
     * @throws IllegalArgumentException if the number cannot be translated to the roman numeral system, i.e. negative numbers and 0.
     */
    override fun toText(value: Int): String {
        validateIntCanBeTranslated(value)
        var remainder = value

        return buildString {
            for (romanNumeral in RomanNumeral.DESCENDING_ORDER) {
                val occurrences = remainder / romanNumeral.value

                if (occurrences > 0) {
                    append(romanNumeral.symbol.repeat(occurrences))
                }
                remainder %= romanNumeral.value
            }
        }
    }

    /**
     * @throws IllegalArgumentException if the text is empty or blank.
     */
    override fun fromText(text: String): Int {
        validateTextCanBeTranslated(text)
        var result = 0

        text.windowed(
            TWO_CHARACTERS_AT_A_TIME,
            ITERATE_BY_ONE_CHARACTER,
            partialWindows = true
        ) { chars ->
            val currentRomanNumeral = toRomanNumeral(chars.first())
            val nextRomanNumeral = chars.lastOrNull()?.let { toRomanNumeral(it) }

            if (nextRomanNumeral == null) {
                result += currentRomanNumeral.value
            } else if (currentRomanNumeral.value < nextRomanNumeral.value) {
                result -= currentRomanNumeral.value
            } else {
                result += currentRomanNumeral.value
            }
        }
        return result
    }

    companion object {

        // These constants are used to make the windowed function more readable
        const val TWO_CHARACTERS_AT_A_TIME = 2
        const val ITERATE_BY_ONE_CHARACTER = 1

        private fun validateIntCanBeTranslated(value: Int) {
            if (value == 0) {
                throw IllegalArgumentException(ZERO_NOT_SUPPORTED.message)
            }
            if (value < 0) {
                throw IllegalArgumentException(NEGATIVE_VALUES_NOT_SUPPORTED.message)
            }
        }

        private fun validateTextCanBeTranslated(text: String) {
            if (text.isBlank()) {
                throw IllegalArgumentException(TEXT_CANNOT_BE_BLANK.message)
            }
        }

        // The relationship between string/char as it relates to `enumValueOf` is a bit awkward/verbose;
        // enumValueOf is generally preferred to Enum.valueOf in Kotlin because it has a reified type
        private fun toRomanNumeral(character: Char): RomanNumeral {
            val symbol = character.toString()
            if (!RomanNumeral.VALID_SYMBOLS.contains(symbol)) {
                throw IllegalArgumentException(INVALID_SYMBOL.message)
            }
            return enumValueOf<RomanNumeral>(symbol)
        }
    }
}