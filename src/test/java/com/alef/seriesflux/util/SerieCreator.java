package com.alef.seriesflux.util;

import com.alef.seriesflux.entities.Serie;

public class SerieCreator {

    public static Serie createSerieToBeSaved() {
        return Serie.builder()
                .title("A volta dos que não foram")
                .genres("Comédia")
                .datee("2020")
                .build();
    }

    public static Serie createValidSerie() {
        return Serie.builder()
                .id("1")
                .title("A volta dos que não foram")
                .genres("Comédia")
                .datee("2020")
                .build();
    }

    public static Serie createValidUpdateSerie() {
        return Serie.builder()
                .id("1")
                .title("A volta dos que não foram")
                .genres("Comédia")
                .datee("2020")
                .build();
    }
}
