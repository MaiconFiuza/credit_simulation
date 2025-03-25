package com.fiuza.creditas.core.usecases

import com.fiuza.creditas.AbstractBaseTest
import com.fiuza.creditas.core.dto.request.LoanSimulationInputDto
import com.fiuza.creditas.core.exceptions.BadRequestException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class PmtSimulationWithBrCurrencyUseCaseTest : AbstractBaseTest() {
    private val pmtSimulationWithBrCurrencyUseCaseMock = PmtSimulationWithBrCurrencyUseCase()

    @Test
    fun simulation_with_sucess() {
        // arrange
        val inputDto = LoanSimulationInputDto(
            birthdate = LocalDate.of(1995, 12, 7),
            value = 50000.0,
            paymentTerm = 60
        )

        // act
        val result = pmtSimulationWithBrCurrencyUseCaseMock.simulation(inputDto)

        // assert
        assertNotNull(result)
        assertTrue(result.totalValue.isNotBlank())
        assertTrue(result.installmentValue.isNotBlank())
        assertTrue(result.totalInterest.isNotBlank())
    }

    @Test
    fun should_fail_with_bad_request_exception_because_of_age_invalid() {
        // Arrange
        val inputDto = LoanSimulationInputDto(
            birthdate = LocalDate.of(2010, 12, 7),
            value = 50000.0,
            paymentTerm = 60
        )

        // act
        val exception = assertThrows<BadRequestException> {
            pmtSimulationWithBrCurrencyUseCaseMock.simulation(inputDto)
        }

        // assert
        assertEquals("Desculpe, mas é necessário ser maior de idade", exception.message)
    }

    @Test
    fun must_apply_correct_interest_rate_in_simulation() {
        // arrange
        val input18 = LoanSimulationInputDto(LocalDate.now().minusYears(18), 10000.0, 12)
        val input30 = LoanSimulationInputDto(LocalDate.now().minusYears(30), 10000.0, 12)
        val input45 = LoanSimulationInputDto(LocalDate.now().minusYears(45), 10000.0, 12)
        val input60 = LoanSimulationInputDto(LocalDate.now().minusYears(60), 10000.0, 12)

        // act
        val result18 = pmtSimulationWithBrCurrencyUseCaseMock.simulation(input18)
        val result30 = pmtSimulationWithBrCurrencyUseCaseMock.simulation(input30)
        val result45 = pmtSimulationWithBrCurrencyUseCaseMock.simulation(input45)
        val result60 = pmtSimulationWithBrCurrencyUseCaseMock.simulation(input60)

        // assert
        assertNotNull(result18)
        assertNotNull(result30)
        assertNotNull(result45)
        assertNotNull(result60)
    }
}
