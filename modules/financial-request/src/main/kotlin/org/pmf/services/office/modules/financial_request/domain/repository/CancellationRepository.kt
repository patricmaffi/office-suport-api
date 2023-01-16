package org.pmf.services.office.modules.financial_request.domain.repository

import org.pmf.services.office.modules.financial_request.domain.entity.Cancellation

interface CancellationRepository {
    fun findByTransactionId(transactionId: String): Cancellation
}
