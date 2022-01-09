package com.alef.seriesflux.controllers;

import com.alef.seriesflux.entities.Serie;
import com.alef.seriesflux.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/serie")
public class SerieController {

    private final SerieService serieService;

    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/series")
    public Flux<Serie> findAll() {
        return serieService.findAll();
    }

//    @GetMapping("/{id}")
//    public Mono<Serie> findById(@PathVariable String id) {
//        return serieService.findById(id);
//    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Serie>> findById(@PathVariable String id) {
        Mono<Serie> serie = serieService.findById(id);
        return serie.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Serie> create(@RequestBody Serie serie) {
        return serieService.create(serie);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Serie>> update(@PathVariable String id, @RequestBody Serie serie) {
        return serieService.update(id, serie)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable  String id) {
        return serieService.delete(id);
    }
}
