package org.pmf.services.office.modules.financial_request.domain.entity

import org.pmf.services.office.core.value_object.Money
import org.pmf.services.office.modules.financial_request.infrastructure.repository.BillingDto
import org.pmf.services.office.modules.financial_request.infrastructure.repository.TransactionTypeDto
import kotlin.math.roundToInt

class Billing(
    val amount: Float
)

enum class TransactionType {
    MILES,
    OTA
}

class Transaction private constructor(
    val id: String,
    val type: TransactionType,
    val totalAmount: Money
) {
    companion object {
        fun createFromDto(
            id: String,
            billings: List<BillingDto>,
            type: TransactionTypeDto
        ): Transaction {

            val transactionType = if (type === TransactionTypeDto.OTA) TransactionType.OTA else TransactionType.MILES

            val totalAmount = billings.fold(0.00) { total, billing ->
                billing.amount + total
            }

            return Transaction(
                id = id,
                type = transactionType,
                totalAmount = Money.from((totalAmount * 100.0).roundToInt() )
            )

        }
    }
}
