package com.fiuza.creditas.core.usecases

import com.fiuza.creditas.core.dto.LoanSimulationDto
import com.fiuza.creditas.core.dto.request.LoanSimulationInputDto
import com.fiuza.creditas.core.entities.Simulation
import com.fiuza.creditas.core.exceptions.BadRequestException
import com.fiuza.creditas.core.exceptions.InternalServerErrorException
import com.fiuza.creditas.core.helper.Formatter
import com.fiuza.creditas.core.interfaces.PmtInterface
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period
import kotlin.math.pow

const val DEFAULT_MONTHS = 12

@Service
class PmtSimulationWithBrCurrencyUseCase : PmtInterface {

    private val logger: Logger = LoggerFactory.getLogger(PmtSimulationWithBrCurrencyUseCase::class.java)

    override fun simulation(loanSimulationInputDto: LoanSimulationInputDto): Simulation {
        try {
            val loanSimulationDto = transformToLoanSimulationDto(loanSimulationInputDto)
            val monthlyInterestRate = loanSimulationDto.annualRate / DEFAULT_MONTHS / 100

            val installmentValue = loanSimulationDto.presentValue * monthlyInterestRate / (
                1 - (1 + monthlyInterestRate)
                    .pow(-loanSimulationDto.paymentTerm)
                )

            return with(loanSimulationDto) {
                Simulation(
                    totalValue = Formatter.formatCurrency(installmentValue * paymentTerm),
                    installmentValue = Formatter.formatCurrency(installmentValue),
                    totalInterest = Formatter.formatCurrency((installmentValue * paymentTerm) - presentValue)
                )
            }
        } catch (e: BadRequestException) {
            logger.error("simulation")
            throw BadRequestException(e.message + "")
        } catch (e: Exception) {
            logger.error("simulation")
            throw InternalServerErrorException("Um erro inesperado ocorreu. por favor tente novamente")
        }
    }

    private fun transformToLoanSimulationDto(loanSimulationInputDto: LoanSimulationInputDto): LoanSimulationDto {
        loanSimulationInputDto.validate()
        val age = age(loanSimulationInputDto.birthdate!!)
        val annualRateByAge = annualRateByAge(age)

        return LoanSimulationDto(
            presentValue = loanSimulationInputDto.value,
            annualRate = annualRateByAge,
            paymentTerm = loanSimulationInputDto.paymentTerm
        )
    }

    private fun age(birthdate: LocalDate): Int {
        val today = LocalDate.now()
        return Period.between(birthdate, today).years
    }

    private fun annualRateByAge(age: Int): Double {
        return when {
            age < 18 -> throw BadRequestException("Desculpe, mas é necessário ser maior de idade")
            age <= 25 -> 5.0
            age in 26..40 -> 3.0
            age in 41..60 -> 4.0
            else -> 4.0
        }
    }
}
