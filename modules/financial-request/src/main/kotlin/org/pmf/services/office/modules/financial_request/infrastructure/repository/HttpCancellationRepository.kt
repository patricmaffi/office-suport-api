package org.pmf.services.office.modules.financial_request.infrastructure.repository

import org.pmf.services.office.modules.financial_request.domain.entity.Cancellation
import org.pmf.services.office.modules.financial_request.domain.repository.CancellationRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.time.ZonedDateTime

class CancellationDto(
    val outboundDepartureDate: ZonedDateTime,
    val createdDate: ZonedDateTime
)

class CancellationsDto(
    val data: List<CancellationDto>
)

@Service
class HttpCancellationRepository(
    @Value("\${api-services-urls.cancel-api-url}") val apiUrl: String,
    @Value("\${api-services-urls.cancel-api-token}") val apiToken: String,
    val webClient: WebClient,
) : CancellationRepository {

    override fun findByTransactionId(transactionId: String): Cancellation {
        val cancellation: CancellationDto =
            webClient.get().uri("$apiUrl/cancelation-requests?searchableId={transactionId}", transactionId)
                .headers {
                    it.set("Authorization", apiToken)
                }
                .retrieve()
                .bodyToMono(CancellationsDto::class.java)
                .map {
                    it.data.last()
                }
                .block()!!

        return Cancellation(
            outboundDate = cancellation.outboundDepartureDate,
            createdAt = cancellation.createdDate
        )
    }
}
