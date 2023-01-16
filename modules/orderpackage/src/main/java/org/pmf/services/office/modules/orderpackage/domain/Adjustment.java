package org.pmf.services.office.modules.orderpackage.domain;

import org.pmf.services.office.core.value_object.Money;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Adjustment {

    private AdjustmentType type;
    private Money total;
    private Map<String, Object> metadata;

    public Adjustment() {
        this.metadata = new HashMap<>();
    }

    public Adjustment(
            AdjustmentType type,
            Money total
    ) {
        this();
        this.type = type;
        this.total = total;
    }

    public Adjustment(
            AdjustmentType type,
            Money total,
            Map<String, Object> metadata
    ) {
        this(type, total);
        this.metadata = metadata;
    }

    public AdjustmentType getType() {
        return type;
    }

    public Money getTotal() {
        return total;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Adjustment)) {
            return false;
        }

        Adjustment candidate = (Adjustment) obj;

        return this.type.equals(candidate.getType()) && this.total.equals(candidate.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, total);
    }
}
