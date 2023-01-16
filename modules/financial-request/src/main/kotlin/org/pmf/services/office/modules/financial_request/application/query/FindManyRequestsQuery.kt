package org.pmf.services.office.modules.financial_request.application.query

import org.pmf.services.office.modules.financial_request.domain.entity.RequestType
import java.time.ZonedDateTime

class FindManyRequestsQuery(
    val externalId: String?,
    val startDate: ZonedDateTime?,
    val endDate: ZonedDateTime?,
    val requestType: RequestType?
)
