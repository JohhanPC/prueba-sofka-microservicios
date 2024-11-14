package api_com_bank.customer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public Queue customerRequestQueue() {
        return new Queue("customer-request-queue", false);
    }

    @Bean
    public Queue customerResponseQueue() {
        return new Queue("customer-response-queue", false);
    }
}
