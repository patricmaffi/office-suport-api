package org.pmf.services.office.modules.financial_request.domain.entity

import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root


fun externalIdEqualsTo(externalId: String?): Specification<Request?>? {
    if (externalId == null) {
        return null
    }

    return Specification<Request?> { request: Root<Request?>, cq: CriteriaQuery<*>?, cb: CriteriaBuilder ->
        cb.equal(
            request.get<String>("externalId"),
            externalId
        )
    }
}


fun requestTypeEqualsTo(type: Int?): Specification<Request?>? {
    if (type == null) {
        return null
    }

    return Specification<Request?> { request: Root<Request?>, cq: CriteriaQuery<*>?, cb: CriteriaBuilder ->
        cb.equal(
            request.get<Int?>("requestType"),
            type
        )
    }
}

fun createdAtBetween(createdAtStartDate: ZonedDateTime?, createdAtEndDate: ZonedDateTime?): Specification<Request?>? {
    if (createdAtStartDate == null || createdAtEndDate == null) {
        return null
    }

    return Specification<Request?> { request: Root<Request?>, cq: CriteriaQuery<*>?, cb: CriteriaBuilder ->
        cb.between(
            request.get("createdAt"),
            createdAtStartDate,
            createdAtEndDate
        )
    }
}

interface RequestRepository : JpaRepository<Request?, UUID?>, JpaSpecificationExecutor<Request>
