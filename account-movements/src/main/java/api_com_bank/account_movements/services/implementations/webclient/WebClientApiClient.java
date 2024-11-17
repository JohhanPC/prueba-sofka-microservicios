package api_com_bank.account_movements.services.implementations.webclient;

import api_com_bank.account_movements.services.contracts.webclient.ApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WebClientApiClient implements ApiClient {

    private final WebClient webClient;

    @Override
    public <T> Mono<T> get(String url, Class<T> responseType) {
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(responseType);
    }
}
