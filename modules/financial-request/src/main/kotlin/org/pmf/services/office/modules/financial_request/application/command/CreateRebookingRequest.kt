package org.pmf.services.office.modules.financial_request.application.command

import org.pmf.services.office.core.value_object.Money
import org.pmf.services.office.modules.financial_request.domain.entity.ClientPaymentMethod
import org.pmf.services.office.modules.financial_request.domain.entity.Losses
import org.pmf.services.office.modules.financial_request.domain.entity.RefundMethod

class CreateRebookingRequest(
    val externalId: String,
    val requesterId: String,
    val voucherValue: Money,
    val feeAirline: Money,
    val fee: Money,
    val feeProvider: Money,
    val totalRebookingAmount: Money,
    val totalClientPaidAmount: Money,
    val feePaymentCreditCardId: String? = "",
    val isFeePaymentBilled: Boolean = false,
    val refundMethods: ArrayList<RefundMethod>,
    val clientPaymentMethods: ArrayList<ClientPaymentMethod>,
    val payOfferer: Boolean = false,
    val airline: String,
    val losses : Losses? = null,
)
