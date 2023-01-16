package org.pmf.services.office.modules.financial_request.domain.entity

import org.hibernate.annotations.Columns
import org.hibernate.annotations.Type
import org.pmf.services.office.core.value_object.Money
import java.util.*
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity


@Entity
@DiscriminatorValue("3")
class RefundRequest constructor(

    @Columns(columns = [
        Column(name = "refund_amount_amount"),
        Column(name = "refund_amount_currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    val refundAmount: Money,

    @Column
    val refundReason: String,

    @Column
    val refundAccountType: RefundAccountType,

    externalId: String,
    requesterId: String,
    voucherValue: Money,
    payOfferer: Boolean,
    airline: String,
) : Request(
    externalId = externalId,
    requesterId = requesterId,
    voucherValue = voucherValue,
    payOfferer = payOfferer,
    airline = airline,
)
