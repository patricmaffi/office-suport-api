package org.pmf.services.office.core.infrastructure;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {

    private final UUID id;
    private final MessageType type;
    private final String name;
    private final String namespace;
    private final String version;
    private final LocalDateTime timestamp;
    private final String origin;
    private final String originVersion;
    private final Object body;
    private final UUID correlationId;
    private final UUID causationId;

    public Message(
            UUID id,
            MessageType type,
            String name,
            String namespace,
            String version,
            LocalDateTime timestamp,
            String origin,
            String originVersion,
            Object body,
            UUID correlationId,
            UUID causationId
    ) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.namespace = namespace;
        this.version = version;
        this.timestamp = timestamp;
        this.origin = origin;
        this.originVersion = originVersion;
        this.body = body;
        this.correlationId = correlationId;
        this.causationId = causationId;
    }

    public UUID getId() {
        return id;
    }

    public MessageType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getVersion() {
        return version;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getOrigin() {
        return origin;
    }

    public String getOriginVersion() {
        return originVersion;
    }

    public Object getBody() {
        return body;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public UUID getCausationId() {
        return causationId;
    }
}
