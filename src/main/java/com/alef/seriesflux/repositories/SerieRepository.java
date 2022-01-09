package com.alef.seriesflux.repositories;

import com.alef.seriesflux.entities.Serie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SerieRepository extends ReactiveMongoRepository<Serie, String> {
}
