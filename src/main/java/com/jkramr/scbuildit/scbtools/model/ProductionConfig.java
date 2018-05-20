package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;

import java.time.Duration;

@Data
public class ProductionConfig {
    @NonNull
    private ProductCollection input;
    @NonNull
    private Duration productionDuration;
    @NonNull
    private Double price;
    @NonNull
    private ProductBatch output;
}
