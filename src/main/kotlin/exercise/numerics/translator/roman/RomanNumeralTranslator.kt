package exercise.numerics.translator.roman

import exercise.numerics.translator.NumericTranslator

class RomanNumeralTranslator : NumericTranslator<Int> {

    override fun toText(value: Int) =
        buildString {
            for (romanNumeral in DESCENDING_ROMAN_NUMERALS) {
                val occurrences = value / romanNumeral.value

                if (occurrences > 0) {
                    append(romanNumeral.symbol.repeat(occurrences))
                }
            }
        }

    companion object {
        val DESCENDING_ROMAN_NUMERALS = RomanNumeral.values().sortedByDescending { it.value }
    }
}