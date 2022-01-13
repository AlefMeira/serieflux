package com.alef.seriesflux.service;

import com.alef.seriesflux.entities.Serie;
import com.alef.seriesflux.repositories.SerieRepository;
import com.alef.seriesflux.util.SerieCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class SerieServiceTest {

    @InjectMocks
    private SerieService serieService;

    @Mock
    private SerieRepository serieRepository;

    private final Serie serie = SerieCreator.createValidSerie();

    @BeforeEach
    void setup() {
        BDDMockito.when(serieRepository.findAll()).thenReturn(Flux.just(serie));

        BDDMockito.when(serieRepository.findById(ArgumentMatchers.anyString())).thenReturn(Mono.just(serie));

        BDDMockito.when(serieService.create(SerieCreator.createSerieToBeSaved())).thenReturn(Mono.just(serie));

        BDDMockito.when(serieService.delete(ArgumentMatchers.anyString())).thenReturn(Mono.empty());
    }

    @Test
    @DisplayName("findAll return a flux of Serie")
    void findAll_returnFluxOfSerie_whenSuccessful() {
        StepVerifier.create(serieService.findAll())
                .expectSubscription()
                .expectNext(serie)
                .verifyComplete();
    }

    @Test
    @DisplayName("findById return a Mono of Serie")
    void findById_returnMonoOfSerie_whenSuccessful() {
        StepVerifier.create(serieService.findById(serie.getId()))
                .expectSubscription()
                .expectNext(serie)
                .verifyComplete();
    }

    @Test
    @DisplayName("findById return a mono error serie does not exist")
    void findById_returnMonoError_whenEmptyMonoIsReturned() {
        BDDMockito.when(serieService.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(serieService.findById("1"))
                .expectSubscription()
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    @DisplayName("create return a mono serie of save")
    void save_createSerie_whenSuccessFullyCreated() {
        Serie serieToBeSaved = SerieCreator.createSerieToBeSaved();

        StepVerifier.create(serieService.create(serieToBeSaved))
                .expectSubscription()
                .expectNext(serie)
                .verifyComplete();
    }

    @Test
    @DisplayName("delete return execute with success")
    void delete_serie_whenSuccessFullyDeleted() {
        StepVerifier.create(serieService.delete(ArgumentMatchers.anyString()))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("findById return a mono error serie does not exist")
    void delete_ReturnMonoError_whenEmptyMonoIsReturned() {
        BDDMockito.when(serieService.delete(ArgumentMatchers.anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(serieService.delete("1"))
                .expectSubscription()
                .expectNextCount(0)
                .verifyComplete();
    }

//    @Test
//    @DisplayName("update save updated serie and return successful")
//    void update_saveUpdatedSerie_whenSuccessful() {
//       StepVerifier.create(serieService.update("1",SerieCreator.createValidSerie()))
//               .expectSubscription()
//               .verifyComplete();
//    }

}