package org.pmf.services.office.modules.financial_request.domain.repository

import org.pmf.services.office.modules.financial_request.domain.entity.RebookingRequest
import org.springframework.data.jpa.repository.JpaRepository

import java.util.*

interface RebookingRequestRepository : JpaRepository<RebookingRequest?, UUID?>
