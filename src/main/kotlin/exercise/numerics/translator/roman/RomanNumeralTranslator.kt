package exercise.numerics.translator.roman

import exercise.numerics.translator.NumericTranslator

class RomanNumeralTranslator : NumericTranslator<Int> {

    override fun toText(value: Int): String {
        return ORDERED_NUMERALS.fold("") { accumulator, romanNumeral ->
            val occurrences = value / romanNumeral.value
            if (occurrences < 0) {
               return ""
            }
            return accumulator + romanNumeral.symbol.repeat(occurrences)
        }
    }

    companion object {
        val ORDERED_NUMERALS = RomanNumeral.values().sortedByDescending { it.value }
    }
}