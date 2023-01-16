package org.pmf.services.office.modules.financial_request.domain.entity

import java.time.ZonedDateTime

class Cancellation(
    val outboundDate: ZonedDateTime,
    val createdAt: ZonedDateTime
)
