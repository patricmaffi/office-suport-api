package org.pmf.services.office.modules.financial_request.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Columns
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.pmf.services.office.core.value_object.Money
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "refund_methods")
@TypeDef(name = "json", typeClass = JsonStringType::class)
data class RefundMethod constructor(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID? = UUID.randomUUID(),

    @Column
    val dueDate: ZonedDateTime,

    @Column
    val dueDateType: DueDateType,

    @Columns(columns = [
        Column(name = "refund_method_amount"),
        Column(name = "refund_method_currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    val amount: Money,

    @Column
    @Enumerated(value = EnumType.STRING)
    val type: RefundMethodType,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    var cancellationRequest: CancellationRequest? = null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    var rebookingRequest: RebookingRequest? = null,
)
