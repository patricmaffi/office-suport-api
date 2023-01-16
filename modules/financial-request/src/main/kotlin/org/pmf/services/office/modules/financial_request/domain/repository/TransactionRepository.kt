package org.pmf.services.office.modules.financial_request.domain.repository

import org.pmf.services.office.modules.financial_request.domain.entity.Transaction

interface TransactionRepository {
    fun findTransactionById(transactionId: String): Transaction?
}
