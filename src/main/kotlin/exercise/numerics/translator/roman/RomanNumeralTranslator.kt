package exercise.numerics.translator.roman

import exercise.numerics.translator.NumericTranslator

class RomanNumeralTranslator : NumericTranslator<Int> {

    /**
     * @throws IllegalArgumentException if the number cannot be translated to the roman numeral system, i.e. negative numbers and 0.
     */
    override fun toText(value: Int): String {
        validateIntCanBeTranslated(value)
        var remainder = value

        return buildString {
            for (romanNumeral in DESCENDING_ROMAN_NUMERALS) {
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
        TODO("Not yet implemented")
    }

    companion object {
        val DESCENDING_ROMAN_NUMERALS = RomanNumeral.values().sortedByDescending { it.value }

        private fun validateIntCanBeTranslated(value: Int) {
            if (value == 0) {
                throw IllegalArgumentException("0 is not supported by the roman numeral system")
            }
            if (value < 0) {
                throw IllegalArgumentException("Negative numbers are not supported by the roman numeral system")
            }
        }
    }
}