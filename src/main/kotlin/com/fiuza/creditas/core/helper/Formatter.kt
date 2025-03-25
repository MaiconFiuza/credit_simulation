package com.fiuza.creditas.core.helper

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class Formatter private constructor() {
    companion object {
        fun formatCurrency(value: Double): String {
            val formatter = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"))
            return formatter.format(BigDecimal(value).setScale(2, RoundingMode.HALF_UP))
        }
    }
}
