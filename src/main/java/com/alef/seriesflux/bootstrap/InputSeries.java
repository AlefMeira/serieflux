package com.alef.seriesflux.bootstrap;

import com.alef.seriesflux.entities.Serie;
import com.alef.seriesflux.repositories.SerieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class InputSeries implements CommandLineRunner {

    private final SerieRepository serieRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeriesObjects();
    }

    private synchronized void loadSeriesObjects() {
        log.debug("Loading initial data.  Count is {}", serieRepository.count().block());

        if(serieRepository.count().block() == 0) {

            serieRepository.save(Serie.builder()
                    .title("Breaking Bad")
                    .genres("Drama")
                    .datee("2008")
                    .build())
                    .block();

            serieRepository.save(Serie.builder()
                    .title("La casa de papel")
                    .genres("Drama")
                    .datee("2017")
                    .build())
                    .block();

            serieRepository.save(Serie.builder()
                    .title("Avatar")
                    .genres("adventure")
                    .datee("2011")
                    .build())
                    .block();

            serieRepository.save(Serie.builder()
                    .title("Rick and Morty")
                    .genres("Anime")
                    .datee("2019")
                    .build())
                    .block();

            serieRepository.save(Serie.builder()
                    .title("Black Mirror")
                    .genres("Suspense")
                    .datee("2008")
                    .build())
                    .block();

            serieRepository.save(Serie.builder()
                    .title("Dark")
                    .genres("Drama")
                    .datee("2017")
                    .build())
                    .block();

            serieRepository.save(Serie.builder()
                    .title("Stranger Things")
                    .genres("Horror")
                    .datee("2018")
                    .build())
                    .block();

            serieRepository.save(Serie.builder()
                    .title("Bojack Horseman")
                    .genres("Humor")
                    .datee("2020")
                    .build())
                    .block();

            serieRepository.save(Serie.builder()
                    .title("Anne With an E ")
                    .genres("Humor")
                    .datee("2020")
                    .build())
                    .block();

            serieRepository.save(Serie.builder()
                    .title("The Crown")
                    .genres("Thriller")
                    .datee("2021")
                    .build())
                    .block();

            log.debug("Series Records loaded: {}", serieRepository.count().block());
        }
    }
}
