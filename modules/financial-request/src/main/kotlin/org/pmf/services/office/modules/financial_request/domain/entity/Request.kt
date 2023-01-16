package org.pmf.services.office.modules.financial_request.domain.entity

import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Columns
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.pmf.services.office.core.value_object.Money
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "requests")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "request_type",
    discriminatorType = DiscriminatorType.INTEGER
)
@TypeDef(name = "json", typeClass = JsonStringType::class)
abstract class Request constructor(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    open var id: UUID? = UUID.randomUUID(),

    @Column
    open val externalId: String,

    @Column
    open val requesterId: String,

    @Columns(columns = [
        Column(name = "voucher_value_amount"),
        Column(name = "voucher_value_currency", length = 3)
    ])
    @Type(type = "org.pmf.services.office.modules.financial_request.domain.MoneyType")
    open val voucherValue: Money,

    @Column
    open val payOfferer: Boolean? = false,

    @Column
    @CreatedDate
    open val createdAt: ZonedDateTime? = ZonedDateTime.now(),

    @Column
    @LastModifiedDate
    open val updatedAt: ZonedDateTime? = ZonedDateTime.now(),

    @Column
    open val airline: String,

    @OneToOne(
        cascade = [CascadeType.ALL],
        mappedBy = "request",
        targetEntity = Losses::class
    )
    val losses: Losses? = null,

    @Column(name = "request_type", insertable = false, updatable = false)
    open val requestType: String? = null,
) {
}
