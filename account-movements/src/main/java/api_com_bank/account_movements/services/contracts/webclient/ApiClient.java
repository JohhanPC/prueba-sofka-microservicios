package api_com_bank.account_movements.services.contracts.webclient;

import reactor.core.publisher.Mono;

public interface ApiClient {

    <T> Mono<T> get(String url, Class<T> responseType);
}
