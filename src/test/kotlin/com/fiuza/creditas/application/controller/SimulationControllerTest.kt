package com.fiuza.creditas.application.controller

import com.fiuza.creditas.AbstractBaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SimulationControllerTest : AbstractBaseTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun get_simulation_with_success() {
        // arrange
        val birthdate = "07/12/1995"
        val value = 50000.0
        val paymentTerm = 60

        // act e assert
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("/simulation")
                .param("birthdate", birthdate)
                .param("value", value.toString())
                .param("paymentTerm", paymentTerm.toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)

        assertThat(result).isNotNull
    }
}
