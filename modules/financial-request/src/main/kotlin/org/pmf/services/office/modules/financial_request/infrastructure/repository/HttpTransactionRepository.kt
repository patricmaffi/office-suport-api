package org.pmf.services.office.modules.financial_request.infrastructure.repository

import org.pmf.services.office.modules.financial_request.domain.entity.Transaction
import org.pmf.services.office.modules.financial_request.domain.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

class BillingDto(
    val amount: Float
)

enum class TransactionTypeDto(
    val dto: String
) {
    OTA("ota"),
    MILES("miles")
}

class TransactionDto(
    val id: String,
    val billing: List<BillingDto>,
    val type: String
)

@Service
class HttpTransactionRepository(
    @Value("\${api-services-urls.order-api-url}") val apiUrl: String,
    val webClient: WebClient,
) : TransactionRepository {

    override fun findTransactionById(transactionId: String): Transaction? {
        return try {
            val response: TransactionDto = webClient.get().uri("$apiUrl/stretches/{id}", transactionId)
                .retrieve()
                .bodyToMono(TransactionDto::class.java)
                .block()!!
            var type = response.type
            if(type.equals("transaction", true))
                type = "miles"
            return Transaction.createFromDto(
                id = response.id,
                billings = response.billing,
                type = TransactionTypeDto.valueOf(type.toUpperCase()),
            )
        } catch (exception: WebClientResponseException) {
            when (exception.statusCode) {
                HttpStatus.NOT_FOUND -> null
                else -> throw exception
            }
        }
    }
}
