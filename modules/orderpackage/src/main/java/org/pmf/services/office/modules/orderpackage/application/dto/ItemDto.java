package org.pmf.services.office.modules.orderpackage.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.pmf.services.office.core.value_object.Money;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class ItemDto {

    @NotBlank
    @Length(min = 5, max = 255)
    private final String name;

    @NotNull
    private final String itemCode;

    @NotNull
    @Valid
    private final Money priceUnit;

    @Min(0)
    private final int quantity;

    @Valid
    private final List<AdjustmentDto> adjustments;
    private final Map<String, Object> metadata;

    @JsonCreator
    public ItemDto(
            @JsonProperty("name") String name,
            @JsonProperty("itemCode") String itemCode,
            @JsonProperty("unitPrice") Money priceUnit,
            @JsonProperty("quantity") int quantity,
            @JsonProperty("adjustments") List<AdjustmentDto> adjustments,
            @JsonProperty("metadata") Map<String, Object> metadata
    ) {
        this.name = name;
        this.itemCode = itemCode;
        this.priceUnit = priceUnit;
        this.quantity = quantity;
        this.adjustments = adjustments;
        this.metadata = metadata;
    }

    public String getName() {
        return name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public Money getPriceUnit() {
        return priceUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<AdjustmentDto> getAdjustments() {
        return adjustments;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
