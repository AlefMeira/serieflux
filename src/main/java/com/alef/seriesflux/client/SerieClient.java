package com.alef.seriesflux.client;

import com.alef.seriesflux.entities.Serie;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Component
public class SerieClient {

    WebClient.Builder webClientBuilder;
    private final WebClient client = WebClient.create("http://localhost:8080");

    public Flux<Serie> serieFlux () {
        return client.get()
                .uri("/serie/series")
                .exchangeToFlux(response -> response.bodyToFlux(Serie.class))
                .log("Series: ");
    }







}
