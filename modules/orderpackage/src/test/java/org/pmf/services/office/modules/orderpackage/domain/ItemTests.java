package org.pmf.services.office.modules.orderpackage.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.core.value_object.Money;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;

class ItemTests {

    static Item modelSample() {
        return new Item("FooItem", "FooCode", new Money(10000), 1, new ArrayList<>());
    }

    static Item itemSampleWithMetadata() {

        var metadata = new HashMap<String, Object>();
        metadata.put("foo", "bar");

        return new Item("FooItem", "FooCode", new Money(10000), 1, new ArrayList<>(), metadata);
    }

    @Test
    void testEmptyConstructor() {
        var item = new Item();
        Assertions.assertNotNull(item);
        Assertions.assertNull(item.getItemCode());
        Assertions.assertEquals(1, item.getQuantity());
    }

    @Test
    void testMetadataConstructor() {
        var item = itemSampleWithMetadata();
        Assertions.assertFalse(item.getMetadata().isEmpty());
        Assertions.assertEquals("bar", item.getMetadata().get("foo"));
    }

    @Test
    void testInequality() {
        var item_1 = modelSample();
        var item_2 = modelSample();

        var hash = item_1.hashCode();

        Assertions.assertNotEquals(item_1, item_2);
        Assertions.assertNotEquals(item_1, item_2);
        Assertions.assertEquals(item_1, item_1);
        Assertions.assertNotEquals(item_1, Mockito.mock(Object.class));
    }

    @Test
    void assertDefaultTimeStamps() {
        var item = modelSample();

        Assertions.assertNotNull(item.getCreatedAt());
        Assertions.assertNull(item.getUpdatedAt());
    }
}
