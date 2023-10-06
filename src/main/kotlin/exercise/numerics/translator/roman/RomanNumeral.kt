package exercise.numerics.translator.roman

enum class RomanNumeral(val value: Int, val symbol: String) {
    I(1, "I"),
    IV(4, "IV"),
    V(5, "V"),
    IX(9, "IX"),
    X(10, "X"),
    XL(40, "XL"),
    L(50, "L"),
    XC(90, "XC"),
    C(100, "C"),
    CD(400, "CD"),
    D(500, "D"),
    CM(900, "CM"),
    M(1000, "M")
}