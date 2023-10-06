package exercise.numerics.translator

/**
 * Represents a service capable of translating between text and numeric representations.
 */
interface NumericTranslator<T> {

    /**
     * Translates a numeric value to a textual representation based on the implementor's semantics.
     */
    fun toText(value: T): String

    /**
     * Translates a textual representation to its numeric value based on the implementor's semantics.
     */
    fun fromText(text: String): T
}