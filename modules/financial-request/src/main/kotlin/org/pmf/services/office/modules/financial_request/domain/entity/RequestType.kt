package org.pmf.services.office.modules.financial_request.domain.entity

enum class RequestType(val sequence: Int, val conventional: String) {
    CANCELLATION(1, "cancellation"),
    REBOOKING(2, "rebooking"),
    REFUND(3, "refund"),
}
