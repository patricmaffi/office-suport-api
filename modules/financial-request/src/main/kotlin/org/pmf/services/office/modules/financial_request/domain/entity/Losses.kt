package org.pmf.services.office.modules.financial_request.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Columns
import org.hibernate.annotations.Type
import org.pmf.services.office.core.value_object.Money
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "losses")
class Losses(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID? = UUID.randomUUID(),

    @Columns(columns = [
        Column(name = "losses_value_amount"),
        Column(name = "losses_value_currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    open val amount: Money,

    @Column
    open val details: String,

    @Column
    @Enumerated(value = EnumType.STRING)
    val payer: LossPayerType,

    @Column
    @Enumerated(value = EnumType.STRING)
    val reason: LossReasonType,

    @Column
    open val approver: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    var request: Request? = null,
)
