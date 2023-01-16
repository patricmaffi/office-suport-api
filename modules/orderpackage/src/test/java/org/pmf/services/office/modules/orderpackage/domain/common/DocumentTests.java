package org.pmf.services.office.modules.orderpackage.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DocumentTests {

    public static Document modelSample() {
        return new Document(
                "FooDocument",
                DocumentType.NATURAL
        );
    }

    @Test
    void testEmptyConstructor() {
        var address = modelSample();
        Assertions.assertNotNull(address);
    }

    @Test
    void testConstructor() {

        var document = modelSample();

        Assertions.assertNotNull(document);
        Assertions.assertEquals("FooDocument", document.getValue());
        Assertions.assertEquals(DocumentType.NATURAL, document.getPersonType());
    }

    @Test
    void testEquality() {
        var document_1 = modelSample();
        var document_2 = modelSample();
        Assertions.assertEquals(document_1, document_2);
    }

    @Test
    void testInequality() {
        var document_1 = modelSample();
        var document_2 = new Object();
        Assertions.assertNotEquals(document_1, document_2);
        var hash = document_1.hashCode();
    }
}
