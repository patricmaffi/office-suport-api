package org.pmf.services.office.modules.financial_request.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class RestClientConfig {

    @Bean
    fun restClient(): WebClient = WebClient.create()
}
