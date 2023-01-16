package org.pmf.services.office.modules.financial_request.application.command

import org.pmf.services.office.modules.financial_request.domain.entity.CancellationRequest
import org.pmf.services.office.modules.financial_request.domain.entity.Transaction
import org.pmf.services.office.modules.financial_request.domain.entity.TransactionType
import org.pmf.services.office.modules.financial_request.domain.exception.InvalidTransactionException
import org.pmf.services.office.modules.financial_request.domain.repository.CancellationRepository
import org.pmf.services.office.modules.financial_request.domain.repository.CancellationRequestRepository
import org.pmf.services.office.modules.financial_request.domain.repository.TransactionRepository
import org.springframework.stereotype.Component

@Component
class DeleteCancellationRequestHandler(
    val cancellationRequestRepository: CancellationRequestRepository
) {

    fun handle(externalId: String) {
        val relatedRequests: List<CancellationRequest> = cancellationRequestRepository.findByExternalId(externalId)
        relatedRequests.forEach { request ->
            cancellationRequestRepository.delete(request)
        }
    }

}
