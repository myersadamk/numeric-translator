package exercise.numerics.translator

/**
 * Represents a service capable of translating between text and numeric representations.
 */
interface NumericTranslator<T> {
    /**
     * Translates an integer value to a textual representation based on the implementor's semantics.
     */
    fun toText(value: T): String
}