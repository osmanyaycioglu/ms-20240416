package org.training.microservice.msorder.integration.models;

import lombok.Data;

@Data
public class SendMessage {
    private String message;
    private String number;
}
