package com.fiuza.creditas.core.dto.request

import com.fiuza.creditas.AbstractBaseTest
import com.fiuza.creditas.core.exceptions.BadRequestException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class LoanSimulationInputDtoTest : AbstractBaseTest() {

    @Test
    fun `#validate_should_thrown_with_value`() {
        // arrange
        val loanSimulationInputdto = LoanSimulationInputDto(
            birthdate = LocalDate.of(1995, 12, 7),
            value = 0.0,
            paymentTerm = 0
        )

        // act e assert
        val exception = assertThrows<BadRequestException> {
            loanSimulationInputdto.validate()
        }

        assert(exception.message?.contains("O valor do empréstimo deve ser maior que zero.") == true)
    }

    @Test
    fun `#validate_should_thrown_with_paymentTerm`() {
        // arrange
        val loanSimulationInputdto = LoanSimulationInputDto(
            birthdate = LocalDate.of(1995, 12, 7),
            value = 50000.0,
            paymentTerm = 0
        )

        // act e assert
        val exception = assertThrows<BadRequestException> {
            loanSimulationInputdto.validate()
        }

        assert(exception.message?.contains("O prazo de pagamento deve ser maior que zero.") == true)
    }

    @Test
    fun `#validate_should_thrown_with_birthdate`() {
        // arrange
        val loanSimulationInputdto = LoanSimulationInputDto(
            birthdate = null,
            value = 50000.0,
            paymentTerm = 60
        )

        // act e assert
        val exception = assertThrows<BadRequestException> {
            loanSimulationInputdto.validate()
        }

        assert(exception.message?.contains("A data de nascimento está no formato incorreto.") == true)
    }
}
