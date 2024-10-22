package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.dtos.BinlistResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BinlistService {

    private final WebClient webClient;

    public BinlistService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://lookup.binlist.net").build();
    }

    public Mono<String> getCountryCodeFromCard(String cardNumber) {
        return webClient.get()
                .uri("/{cardNumber}", cardNumber)
                .retrieve()
                .bodyToMono(BinlistResponse.class)
                .map(binlistResponse -> {
                    BinlistResponse.Country country = binlistResponse.getCountry();
                    return country != null ? country.getAlpha2() : null;
                })
                .onErrorResume(e -> Mono.error(new RuntimeException("Could not retrieve country information from Binlist")));
    }
}
