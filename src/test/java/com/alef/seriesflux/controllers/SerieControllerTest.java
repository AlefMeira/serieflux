package com.alef.seriesflux.controllers;

import com.alef.seriesflux.entities.Serie;
import com.alef.seriesflux.service.SerieService;
import com.alef.seriesflux.util.SerieCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class SerieControllerTest {

    @Mock
    private SerieService serieService;

    @InjectMocks
    private SerieController serieController;

    private Serie serie = SerieCreator.createValidSerie();

    @BeforeEach
    public void setUp() {
        BDDMockito.when(serieService.findAll()).thenReturn(Flux.just(serie));

        BDDMockito.when(serieService.findById("1")).thenReturn(Mono.just(serie));

        BDDMockito.when(serieService.create(serie)).thenReturn(Mono.just(serie));

        BDDMockito.when(serieService.update("1", serie)).thenReturn(Mono.just(serie));
    }
    @Test
    @DisplayName("lisAll returns a  flux of series")
    public void findAll_ReturnFluxSerie_whenSuccessful() {
        StepVerifier.create(serieController.findAll())
                .expectSubscription()
                .expectNext(serie)
                .verifyComplete();
    }

    @Test
    @DisplayName("find by id return a mono of series")
    public void findById_ReturnMonoOfSeries_WhenSuccessful() {
        StepVerifier.create(serieController.findById("1"))
                .expectSubscription()
                .expectComplete();

    }

    @Test
    @DisplayName("create return successful")
    public void create_ReturnMonoOfSerie_WhenSuccessful() {
        StepVerifier.create(serieController.create(serie))
                .expectSubscription()
                .expectNext(serie)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a successful when update of mono series")
    public void update_ReturnMonoSuccessfulUpdate() {
        StepVerifier.create(serieController.update("1", serie))
                .expectSubscription()
                .expectComplete();
    }
}