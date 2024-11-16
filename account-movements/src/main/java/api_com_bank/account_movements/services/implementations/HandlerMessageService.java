package api_com_bank.account_movements.services.implementations;

import api_com_bank.account_movements.config.CustomRabbitTemplate;
import api_com_bank.account_movements.dtos.message.AccountCreatedMessage;
import api_com_bank.account_movements.dtos.message.CustomerCreatedMessage;
import api_com_bank.account_movements.services.contracts.IHandlerMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandlerMessageService implements IHandlerMessage {

    private final CustomRabbitTemplate rabbitTemplate;
    private final AccountInternalService accountInternalService;

    @Override
    public void sendMessage(AccountCreatedMessage message) {
        log.info("Send message of account created: {}", message);
        rabbitTemplate.convertAndSend("notification-queue", message);
    }

    @Override
    @RabbitListener(queues = "customer-created-notification-queue")
    public void readMessage(CustomerCreatedMessage message) {
        log.info("Message received from RabbitMQ queue: {}", message);
        AccountCreatedMessage accountCreatedMessage = accountInternalService.processCustomerMessage(message);
        sendMessage(accountCreatedMessage);
    }

}
