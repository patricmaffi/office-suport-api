package org.pmf.services.office.modules.financial_request.domain.entity

import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Columns
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.pmf.services.office.core.value_object.Money
import org.pmf.services.office.modules.financial_request.domain.exception.RefundAmountExceededTransactionTotalValueException
import javax.persistence.*

@Entity
@DiscriminatorValue("2")
@TypeDef(name = "json", typeClass = JsonStringType::class)
class RebookingRequest constructor(

    @Columns(columns = [
        Column(name = "fee_airline_amount"),
        Column(name = "fee_airline_currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    val feeAirline: Money,

    @Columns(columns = [
        Column(name = "fee__amount"),
        Column(name = "fee__currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    val fee: Money,

    @Columns(columns = [
        Column(name = "fee_provider_amount"),
        Column(name = "fee_provider_currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    val feeProvider: Money,

    @Columns(columns = [
        Column(name = "total_client_paid_amount"),
        Column(name = "total_client_paid_currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    val totalClientPaidAmount: Money,

    @Columns(columns = [
        Column(name = "total_rebooking_amount"),
        Column(name = "total_rebooking_currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    val totalRebookingAmount: Money,

    @Column
    val feePaymentCreditCardId: String? = null,

    @Column
    val isFeePaymentBilled: Boolean,

    @OneToMany(
        cascade = [CascadeType.ALL],
        mappedBy = "rebookingRequest",
        targetEntity = ClientPaymentMethod::class
    )
    var clientPaymentMethods: List<ClientPaymentMethod>,

    @OneToMany(
        cascade = [CascadeType.ALL],
        mappedBy = "rebookingRequest",
        targetEntity = RefundMethod::class
    )
    var refundMethods: List<RefundMethod>,

    totalTransactionAmount: Money,
    externalId: String,
    requesterId: String,
    voucherValue: Money,
    payOfferer: Boolean,
    airline: String,
    losses: Losses?,
) : Request(
    externalId = externalId,
    requesterId = requesterId,
    voucherValue = voucherValue,
    payOfferer = payOfferer,
    airline = airline,
    losses = losses
) {
    init {
        val refundAmount = refundMethods.fold(0) { total, refundMethod ->
            refundMethod.amount.amount + total
        }

        if (refundAmount > totalTransactionAmount.amount) {
            throw RefundAmountExceededTransactionTotalValueException()
        }

        this.refundMethods.forEach { refundMethod ->
            refundMethod.rebookingRequest = this
        }


        this.clientPaymentMethods.forEach { paymentMethod ->
            paymentMethod.rebookingRequest = this
        }
        this.losses?.request = this
    }
}
