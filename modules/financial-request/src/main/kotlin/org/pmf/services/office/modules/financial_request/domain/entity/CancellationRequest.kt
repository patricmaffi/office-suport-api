package org.pmf.services.office.modules.financial_request.domain.entity

import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.*
import org.pmf.services.office.core.value_object.Money
import org.pmf.services.office.modules.financial_request.domain.exception.InvalidRefundMethodDueDateException
import java.time.Period
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.persistence.*
import javax.persistence.CascadeType
import javax.persistence.Entity

@Entity
@DiscriminatorValue("1")
@TypeDef(name = "json", typeClass = JsonStringType::class)
class CancellationRequest private constructor(
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

    @Column
    val feePaymentCreditCardId: String? = null,

    @Columns(columns = [
        Column(name = "total_client_paid_amount"),
        Column(name = "total_client_paid_currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    val totalClientPaidAmount: Money,

    @Column(name = "fee_cia_payment_type")
    val feeCiaPaymentType: String? = "",

    @OneToMany(
        cascade = [CascadeType.ALL],
        mappedBy = "cancellationRequest",
        targetEntity = PassengerPerStretch::class,
        fetch = FetchType.EAGER
    )
    val passengersPerStretch: List<PassengerPerStretch>,

    @Column
    val isPartial: Boolean? = null,

    @OneToMany(
        cascade = [CascadeType.ALL],
        mappedBy = "cancellationRequest",
        targetEntity = RefundMethod::class,
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    var refundMethods: List<RefundMethod>,

    externalId: String,
    requesterId: String,
    voucherValue: Money,
    payOfferer: Boolean? = false,
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
        this.refundMethods.forEach { refundMethod ->
            refundMethod.cancellationRequest = this
        }

        this.passengersPerStretch.forEach { passengerPerStretch ->
            passengerPerStretch.cancellationRequest = this
        }
        this.losses?.request = this
    }

    companion object {

        fun createFromOTA(
            feeAirline: Money,
            fee: Money,
            passengersPerStretch: List<PassengerPerStretch>,
            isPartial: Boolean,
            refundMethods: List<RefundMethod>,
            externalId: String,
            requesterId: String,
            voucherValue: Money,
            airline: String,
            feeCiaPaymentType: String,
            feePaymentCreditCardId: String?,
            relatedTransaction: Transaction,
            cancellationCreationDate: ZonedDateTime,
            outboundDate: ZonedDateTime,
            losses: Losses?,
            payOfferer: Boolean?,
            totalClientPaidAmount: Money
        ): CancellationRequest {

            val refundAmount = refundMethods.fold(0) { total, refundMethod ->
                refundMethod.amount.amount + total
            }
            //#PVK-109 - remover validação total excedido
//            if (refundAmount > relatedTransaction.totalAmount.amount) {
//                throw RefundAmountExceededTransactionTotalValueException()
//            }

            refundMethods.forEach { refundMethod ->
                when (refundMethod.dueDateType) {
                    DueDateType.AFTER_12_MONTHS -> {
                        val diff = Period.between(
                            outboundDate.toInstant().atOffset(ZoneOffset.UTC).toLocalDate(),
                            refundMethod.dueDate.toInstant().atOffset(ZoneOffset.UTC).toLocalDate()
                        )

                        if (diff.years != 1) {
                            throw InvalidRefundMethodDueDateException()
                        }
                    }
                    DueDateType.AFTER_72_HOURS -> {
                        val diff = Period.between(
                            cancellationCreationDate.toInstant().atOffset(ZoneOffset.UTC).toLocalDate(),
                            refundMethod.dueDate.toInstant().atOffset(ZoneOffset.UTC).toLocalDate()
                        )

                        if (diff.days != 3) {
                            throw InvalidRefundMethodDueDateException()
                        }
                    }
                }
            }

            return CancellationRequest(
                feeAirline = feeAirline,
                fee = fee,
                passengersPerStretch = passengersPerStretch,
                isPartial = isPartial,
                refundMethods = refundMethods,
                externalId = externalId,
                requesterId = requesterId,
                voucherValue = voucherValue,
                feeCiaPaymentType = feeCiaPaymentType,
                feePaymentCreditCardId = feePaymentCreditCardId,
                airline = airline,
                losses = losses,
                totalClientPaidAmount = totalClientPaidAmount,
                payOfferer = payOfferer
            )
        }

        fun createFromMiles(
            feeAirline: Money,
            fee: Money,
            passengersPerStretch: List<PassengerPerStretch>,
            isPartial: Boolean,
            refundMethods: List<RefundMethod>,
            externalId: String,
            requesterId: String,
            voucherValue: Money,
            airline: String,
            feePaymentCreditCardId: String?,
            relatedTransaction: Transaction,
            cancellationCreationDate: ZonedDateTime,
            outboundDate: ZonedDateTime,
            losses: Losses?,
            payOfferer: Boolean?,
            totalClientPaidAmount: Money
        ): CancellationRequest {
            val refundAmount = refundMethods.fold(0) { total, refundMethod ->
                refundMethod.amount.amount + total
            }

//            if (refundAmount > relatedTransaction.totalAmount.amount) {
//                throw RefundAmountExceededTransactionTotalValueException()
//            }

            refundMethods.forEach { refundMethod ->
                when (refundMethod.dueDateType) {
                    DueDateType.AFTER_12_MONTHS -> {
                        val diff = Period.between(
                            outboundDate.toInstant().atOffset(ZoneOffset.UTC).toLocalDate(),
                            refundMethod.dueDate.toInstant().atOffset(ZoneOffset.UTC).toLocalDate()
                        )

                        if (diff.years != 1) {
                            throw InvalidRefundMethodDueDateException()
                        }
                    }
                    DueDateType.AFTER_72_HOURS -> {
                        val diff = Period.between(
                            cancellationCreationDate.toInstant().atOffset(ZoneOffset.UTC).toLocalDate(),
                            refundMethod.dueDate.toInstant().atOffset(ZoneOffset.UTC).toLocalDate()
                        )

                        if (diff.days != 3) {
                            throw InvalidRefundMethodDueDateException()
                        }
                    }
                }
            }

            return CancellationRequest(
                feeAirline = feeAirline,
                fee = fee,
                passengersPerStretch = passengersPerStretch,
                isPartial = isPartial,
                refundMethods = refundMethods,
                externalId = externalId,
                requesterId = requesterId,
                voucherValue = voucherValue,
                feePaymentCreditCardId = feePaymentCreditCardId,
                airline = airline,
                losses = losses,
                totalClientPaidAmount = totalClientPaidAmount,
                payOfferer = payOfferer
            )
        }
    }
}
