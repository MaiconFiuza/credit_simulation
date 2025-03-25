package com.fiuza.creditas.core.dto

data class LoanSimulationDto(
    val presentValue: Double,
    val annualRate: Double,
    val paymentTerm: Int
)
