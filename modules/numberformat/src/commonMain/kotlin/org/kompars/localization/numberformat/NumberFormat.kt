package org.kompars.localization.numberformat

public class NumberFormat internal constructor(
    internal val decimalSeparator: Char,
    internal val minimalDecimalDigits: Int = 0,
    internal val maximalDecimalDigits: Int? = null,
    internal val groupSeparator: Char? = null,
    internal val groupSize: Int? = null,
) {
    public fun format(number: String): String {
        val isNegative = number[0] == '-'

        val positiveNumber = when (isNegative) {
            true -> number.substring(1)
            false -> number
        }

        val parts = positiveNumber.split('.')
        val integerPart = parts[0]

        val decimalPart = when (parts.size > 1) {
            true -> parts[1]
            false -> ""
        }

        val strippedDecimalPart = when (maximalDecimalDigits != null && decimalPart.length > maximalDecimalDigits) {
            true -> decimalPart.substring(0, maximalDecimalDigits)
            false -> decimalPart
        }

        val formattedDecimalPart = when (minimalDecimalDigits > 0 && strippedDecimalPart.length < minimalDecimalDigits) {
            true -> strippedDecimalPart.padEnd(minimalDecimalDigits, '0')
            false -> strippedDecimalPart
        }

        val formattedIntegerPart = when (groupSeparator != null && groupSize != null) {
            true -> integerPart.reversed().chunked(groupSize).joinToString(groupSeparator.toString()).reversed()
            false -> integerPart
        }

        val formattedNumber = when (formattedDecimalPart.isNotEmpty()) {
            true -> "$formattedIntegerPart$decimalSeparator$formattedDecimalPart"
            false -> formattedIntegerPart
        }

        return when (isNegative) {
            true -> "-$formattedNumber"
            false -> formattedNumber
        }
    }
}
