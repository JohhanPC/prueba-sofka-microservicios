package api_com_bank.account_movements.services;

import api_com_bank.account_movements.dtos.response.CustomerResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerApiService {

    private final WebClient.Builder webClientBuilder;

    @Value("${customer.service.url}")
    private String customerServiceUrl;

    @Value("${customer.service.path.findByIdentification}")
    private String findByIdentificationPath;

    public Mono<CustomerResponseDTO> getCustomerByIdentificationNumber(String identificationNumber) {
        String url = customerServiceUrl + String.format(findByIdentificationPath, identificationNumber);

        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(CustomerResponseDTO.class)
                .doOnSuccess(customer -> log.info("Customer retrieved: {}", customer))
                .doOnError(error -> log.error("Error retrieving customer data", error));
    }

}
