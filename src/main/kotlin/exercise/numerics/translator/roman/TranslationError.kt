package exercise.numerics.translator.roman

enum class TranslationError(val message: String) {
    TEXT_CANNOT_BE_BLANK("Textual representation cannot be blank"),
    INVALID_SYMBOL("Text must only contain valid roman numeral symbols"),
    ZERO_NOT_SUPPORTED("0 is not supported by the roman numeral system"),
    NEGATIVE_VALUES_NOT_SUPPORTED("Negative numbers are not supported by the roman numeral system"),
}