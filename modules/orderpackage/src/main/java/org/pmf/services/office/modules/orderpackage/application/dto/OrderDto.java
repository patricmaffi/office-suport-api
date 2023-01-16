package org.pmf.services.office.modules.orderpackage.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class OrderDto {

    @NotNull
    private final OrderTypeDto type;

    @NotNull
    @Valid
    private final EntityInformationDto customer;

    @NotNull
    @Valid
    private final EntityInformationDto merchant;

    @NotEmpty
    @Valid
    private final List<ItemDto> items;

    @Valid
    private final List<TransactionDto> transactions;

    private final Map<String, Object> metadata;

    @JsonCreator
    public OrderDto(
            @JsonProperty("type") OrderTypeDto type,
            @JsonProperty("customer") EntityInformationDto customer,
            @JsonProperty("merchant") EntityInformationDto merchant,
            @JsonProperty("items") List<ItemDto> items,
            @JsonProperty("transactions") List<TransactionDto> transactions,
            @JsonProperty("metadata") Map<String, Object> metadata
    ) {
        this.type = type;
        this.customer = customer;
        this.merchant = merchant;
        this.items = items;
        this.transactions = transactions;
        this.metadata = metadata;
    }

    public OrderTypeDto getType() {
        return type;
    }

    public EntityInformationDto getCustomer() {
        return customer;
    }

    public EntityInformationDto getMerchant() {
        return merchant;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
