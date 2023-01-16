package org.pmf.services.office.core.infrastructure;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageFactory {

    private final String namespace;
    private final String origin;
    private final String originVersion;

    public MessageFactory(String namespace, String origin, String originVersion) {
        this.namespace = namespace;
        this.origin = origin;
        this.originVersion = originVersion;
    }

    public Message create(
            MessageType type,
            String name,
            String version,
            Object body
    ) {
        return new Message(
                UUID.randomUUID(),
                type,
                name,
                this.namespace,
                version,
                LocalDateTime.now(),
                this.origin,
                this.originVersion,
                body,
                null,
                null
        );
    }
}
