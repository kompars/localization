package org.kompars.localization.numberformat

public fun NumberFormat(block: NumberFormatBuilder.() -> Unit): NumberFormat {
    return NumberFormatBuilder().apply(block).build()
}

public fun NumberFormat.copy(block: NumberFormatBuilder.() -> Unit): NumberFormat {
    val builder = NumberFormatBuilder(
        decimalSeparator = decimalSeparator,
        minimalDecimalDigits = minimalDecimalDigits,
        maximalDecimalDigits = maximalDecimalDigits,
        groupSize = groupSize,
        groupSeparator = groupSeparator,
    )

    return builder.apply(block).build()
}

public class NumberFormatBuilder internal constructor(
    private var decimalSeparator: Char = '.',
    private var minimalDecimalDigits: Int = 0,
    private var maximalDecimalDigits: Int? = null,
    private var groupSize: Int? = null,
    private var groupSeparator: Char? = null,
) {
    public fun decimalSeparator(separator: Char) {
        decimalSeparator = separator
    }

    public fun minimalDecimalDigits(digits: Int) {
        minimalDecimalDigits = digits
    }

    public fun maximalDecimalDigits(digits: Int?) {
        maximalDecimalDigits = digits
    }

    public fun decimalDigits(min: Int = 0, max: Int? = null) {
        minimalDecimalDigits = min
        maximalDecimalDigits = max
    }

    public fun grouping(size: Int, separator: Char) {
        groupSize = size
        groupSeparator = separator
    }

    public fun disableGrouping() {
        groupSize = null
        groupSeparator = null
    }

    internal fun build(): NumberFormat {
        return NumberFormat(
            decimalSeparator = decimalSeparator,
            minimalDecimalDigits = minimalDecimalDigits,
            maximalDecimalDigits = maximalDecimalDigits,
            groupSeparator = groupSeparator,
            groupSize = groupSize,
        )
    }
}
