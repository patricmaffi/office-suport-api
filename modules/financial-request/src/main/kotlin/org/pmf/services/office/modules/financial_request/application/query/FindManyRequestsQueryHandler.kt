package org.pmf.services.office.modules.financial_request.application.query

import org.pmf.services.office.modules.financial_request.domain.entity.*
import org.springframework.data.jpa.domain.Specification.where
import org.springframework.stereotype.Component

@Component
class FindManyRequestsQueryHandler(
    private val requestRepository: RequestRepository
) {
    fun handle(query: FindManyRequestsQuery): List<Request> {
        return this.requestRepository.findAll(
            where(externalIdEqualsTo(query.externalId)).and(
                createdAtBetween(
                    query.startDate,
                    query.endDate
                )).and(requestTypeEqualsTo(query.requestType?.sequence))
        )
    }
}
