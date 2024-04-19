package org.training.microservice.msnotify;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitListener {


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sms-send", durable = "true", autoDelete = "false"),
            exchange = @Exchange(name = "send-exchange", type = ExchangeTypes.DIRECT),
            key = "sms-send-key"))
    public void handleMessage(SendMessage sendMessageParam) {
        System.out.println("Received SMS : " + sendMessageParam);
    }

}
