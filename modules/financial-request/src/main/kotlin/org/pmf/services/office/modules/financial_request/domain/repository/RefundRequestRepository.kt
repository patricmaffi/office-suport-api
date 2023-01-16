package org.pmf.services.office.modules.financial_request.domain.repository

import org.pmf.services.office.modules.financial_request.domain.entity.RefundRequest
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefundRequestRepository : JpaRepository<RefundRequest?, UUID?>
