package api_com_bank.account_movements.config.rabbitMQ;

import api_com_bank.account_movements.exceptions.MessageSendingException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomRabbitTemplate extends RabbitTemplate {

    public CustomRabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        super(connectionFactory);
        this.setMessageConverter(converter);
    }

    @Override
    public void convertAndSend(@NonNull String exchange, @NonNull String routingKey, @NonNull Object message) {
        try {
            super.convertAndSend(exchange, routingKey, message);
        } catch (AmqpException ex) {
            throw new MessageSendingException("Error to send message to RabbitMQ", ex);
        }
    }

    @Override
    public void convertAndSend(@NonNull String routingKey, @NonNull Object message) {
        try {
            super.convertAndSend(routingKey, message);
        } catch (AmqpException ex) {
            throw new MessageSendingException("Error to send message to RabbitMQ", ex);
        }
    }
}
