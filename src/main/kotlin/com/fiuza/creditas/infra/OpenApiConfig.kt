package com.fiuza.creditas.infra

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition
@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Documentação simulação de empréstimo")
                    .version("1.0")
                    .description("Documentação da API gerada para o teste técnico para vaga de Jr na Créditas")
            )
    }
}
