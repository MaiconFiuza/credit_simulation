package com.fiuza.creditas.core.dto.request

import com.fiuza.creditas.core.exceptions.BadRequestException
import java.time.LocalDate

data class LoanSimulationInputDto(
    val birthdate: LocalDate?,
    val value: Double,
    val paymentTerm: Int
) {
    fun validate() {
        if (value <= 0) {
            throw BadRequestException("O valor do empréstimo deve ser maior que zero.")
        }

        if (paymentTerm <= 0) {
            throw BadRequestException("O prazo de pagamento deve ser maior que zero.")
        }

        if (birthdate == null) {
            throw BadRequestException("A data de nascimento está no formato incorreto.")
        }
    }
}
