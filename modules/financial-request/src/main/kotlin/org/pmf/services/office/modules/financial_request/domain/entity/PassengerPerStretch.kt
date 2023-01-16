package org.pmf.services.office.modules.financial_request.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "passenger_per_stretch")
class PassengerPerStretch(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID? = UUID.randomUUID(),

    @Column
    @Enumerated(value = EnumType.STRING)
    val stretch: Stretch,

    @Column
    val passengerId: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    var cancellationRequest: CancellationRequest? = null,
)
