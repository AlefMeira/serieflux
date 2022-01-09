package com.alef.seriesflux.service;

import com.alef.seriesflux.entities.Serie;
import com.alef.seriesflux.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SerieService {

    private final SerieRepository serieRepository;

    @Autowired
    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public Flux<Serie> findAll() {
        return serieRepository.findAll();
    }

    public Mono<Serie> findById(String id) {
        return serieRepository.findById(id);
    }

    public Mono<Serie> create(Serie serie) {
        return serieRepository.save(serie);
    }

    public Mono<Serie> update(String id, Serie serie) {
        return serieRepository.findById(id)
                .flatMap(dbSerie -> {
                    dbSerie.setTitle(serie.getTitle());
                    dbSerie.setGenres(serie.getGenres());
                    dbSerie.setDatee(serie.getDatee());
                    return serieRepository.save(dbSerie);
                });
    }

    public Mono<Void> delete(String id) {
        return serieRepository.deleteById(id);
    }
}
