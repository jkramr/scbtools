package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString(exclude = {"factory", "config"})
public class Production {

    private final Factory factory;
    private final Map<String, Duration> config;
    private final Map<String, Long> batches;

    public Production(ProductBatch product, Long count, Factory factory, Duration productionDuration) {
        this.batches = new HashMap<>();
        this.config = new HashMap<>();
        this.factory = factory;
        batches.put(product.getProductKey(), product.getAmount() * count);
        config.put(product.getProductKey(), productionDuration);
    }


    public Production addProducts(ProductBatch product, Long count, Duration productionDuration) {
        batches.computeIfAbsent(product.getProductKey(), (key) -> batches.put(key, product.getAmount() * count));
        batches.computeIfPresent(product.getProductKey(), (key, value) -> batches.put(key, value + product.getAmount() * count));
        config.computeIfAbsent(product.getProductKey(), (key) -> config.put(key, productionDuration));
        return this;
    }
}
