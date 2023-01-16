package org.pmf.services.office.modules.financial_request.domain.repository

import org.pmf.services.office.modules.financial_request.domain.entity.CancellationRequest
import org.pmf.services.office.modules.financial_request.domain.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CancellationRequestRepository : JpaRepository<CancellationRequest?, UUID?>
{
    fun findByExternalId(externalId: String): List<CancellationRequest>
}
