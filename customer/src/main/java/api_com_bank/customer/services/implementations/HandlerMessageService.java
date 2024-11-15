package api_com_bank.customer.services.implementations;

import api_com_bank.customer.config.CustomRabbitTemplate;
import api_com_bank.customer.dtos.message.CustomerCreatedMessage;
import api_com_bank.customer.services.contracts.IHandlerMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandlerMessageService implements IHandlerMessage {

    private final CustomRabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(CustomerCreatedMessage message) {
        log.info("Send response to RabbitMQ: {}", message);
        rabbitTemplate.convertAndSend("customer-created-notification-queue", message);
    }

}
