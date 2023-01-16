package org.pmf.services.office.modules.financial_request

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfigration.class})
@PropertySource("classpath:financial-request-application.properties")
class FinancialRequestApplication

fun main(args: Array<String>) {
    runApplication<org.pmf.services.office.modules.financial_request.FinancialRequestApplication>(*args)
}
