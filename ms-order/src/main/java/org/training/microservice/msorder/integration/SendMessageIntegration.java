package org.training.microservice.msorder.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.training.microservice.msorder.integration.models.SendMessage;

@Service
@RequiredArgsConstructor
public class SendMessageIntegration {
    private final RabbitTemplate rabbitTemplate;

    public void sendSMS(String message,
                        String number) {
        SendMessage sendMessageLoc = new SendMessage();
        sendMessageLoc.setMessage(message);
        sendMessageLoc.setNumber(number);
        rabbitTemplate.convertAndSend("send-exchange",
                                      "sms-send-key",
                                      sendMessageLoc);
    }

}
