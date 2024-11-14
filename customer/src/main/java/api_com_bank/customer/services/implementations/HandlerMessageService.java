package api_com_bank.customer.services.implementations;

import api_com_bank.customer.config.CustomRabbitTemplate;
import api_com_bank.customer.services.contracts.IHandlerMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandlerMessageService implements IHandlerMessage {

    private final CustomRabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Object response) {
        log.info("Enviando respuesta a RabbitMQ: {}", response);
        rabbitTemplate.convertAndSend("customer-response-queue", response);
    }

    @Override
    @RabbitListener(queues = "customer-request-queue")
    public void readMessage(String identificationId) {
        log.info("Mensaje recibido de RabbitMQ con identificación: {}", identificationId);
        sendMessage("mensaje recibido: " + identificationId);
    }
}
