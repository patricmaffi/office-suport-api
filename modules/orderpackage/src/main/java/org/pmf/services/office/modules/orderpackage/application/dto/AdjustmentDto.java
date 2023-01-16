package org.pmf.services.office.modules.orderpackage.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pmf.services.office.core.value_object.Money;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class AdjustmentDto {

    private final AdjustmentTypeDto type;

    @Valid
    @NotNull
    private final Money total;

    private final Map<String, Object> metadata;

    @JsonCreator
    public AdjustmentDto(
            @JsonProperty("type") AdjustmentTypeDto type,
            @JsonProperty("total") Money total,
            @JsonProperty("metadata") Map<String, Object> metadata) {
        this.type = type;
        this.total = total;
        this.metadata = metadata;
    }

    public AdjustmentTypeDto getType() {
        return type;
    }

    public Money getTotal() {
        return total;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
