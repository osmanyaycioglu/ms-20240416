package org.training.microservice.msorder.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMessageIntegration {
    private final RabbitTemplate rabbitTemplate;

    public void sendSMS(String message,
                        String number) {
        rabbitTemplate.convertAndSend("send-exchange",
                                      "sms-send-key",
                                      message);
    }

}
