package com.fiuza.creditas.core.interfaces

import com.fiuza.creditas.core.dto.request.LoanSimulationInputDto
import com.fiuza.creditas.core.entities.Simulation

interface PmtInterface {

    fun simulation(loanSimulationInputDto: LoanSimulationInputDto): Simulation
}
