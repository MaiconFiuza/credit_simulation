package com.fiuza.creditas.application.controller

import com.fiuza.creditas.core.dto.request.LoanSimulationInputDto
import com.fiuza.creditas.core.entities.Simulation
import com.fiuza.creditas.core.usecases.PmtSimulationWithBrCurrencyUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/simulation")
class SimulationController(
    private val pmtSimulationWIthBrCurrencyUseCase: PmtSimulationWithBrCurrencyUseCase
) {
    private val logger: Logger = LoggerFactory.getLogger(SimulationController::class.java)

    @Operation(
        description = "Realiza simulação de empréstimo",
        summary = "Endpoint responsável por retornar a simulação de empréstimo",
        responses = [ApiResponse(description = "OK", responseCode = "200")]
    )
    @GetMapping
    fun getSimulation(
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @RequestParam(required = true)
        birthdate: LocalDate,
        @RequestParam(required = true) value: Double,
        @RequestParam(required = true) paymentTerm: Int
    ): ResponseEntity<Simulation> {
        logger.info("get /simulation")
        val loanSimulationInputDto = LoanSimulationInputDto(
            birthdate = birthdate,
            value = value,
            paymentTerm = paymentTerm
        )
        val result = pmtSimulationWIthBrCurrencyUseCase.simulation(loanSimulationInputDto = loanSimulationInputDto)

        return ResponseEntity.ok()
            .body(result)
    }
}
