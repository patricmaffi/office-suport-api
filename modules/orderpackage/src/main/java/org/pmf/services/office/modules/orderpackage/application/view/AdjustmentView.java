package org.pmf.services.office.modules.orderpackage.application.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.domain.AdjustmentType;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdjustmentView {

    private AdjustmentType type;
    private Money total;
    private Map<String, Object> metadata;

    public AdjustmentType getType() {
        return type;
    }

    public Money getTotal() {
        return total;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public AdjustmentView withType(AdjustmentType type) {
        this.type = type;
        return this;
    }

    public AdjustmentView withTotal(Money total) {
        this.total = total;
        return this;
    }

    public AdjustmentView withMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }
}
