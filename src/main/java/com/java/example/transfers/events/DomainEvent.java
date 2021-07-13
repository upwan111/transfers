package com.java.example.transfers.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class DomainEvent {

    protected String aggregateId;
    protected String aggregateType;
    protected String eventId;
    protected String eventName;
    protected Instant generatedDate;

    public DomainEvent(String eventName, String aggregateId, String aggregateType) {
        this.eventId = UUID.randomUUID().toString();
        this.eventName = eventName;
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.generatedDate = Instant.now();
    }
}
