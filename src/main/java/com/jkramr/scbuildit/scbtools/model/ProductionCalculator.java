package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class ProductionCalculator {

    @NonNull
    private Farm farm;
    private ProductionStrategy strategy;
    private Map<String, Long> productGraph;
    private Map<Factory, Production> productionFactoryGraph;
    private Map<Factory, Duration> productionTimeGraph;

    public ProductionCalculator(Farm farm, ProductionStrategy strategy) {
        this.farm = farm;
        this.strategy = strategy;
        productGraph = new HashMap<>();
        productionFactoryGraph = new HashMap<>();
        productionTimeGraph = new HashMap<>();
    }

    public void calculate(ProductBatch... products) {
        for (ProductBatch product : products) {
            calculate(product, 1L, 0);
        }

    }

    private void calculate(ProductBatch batch, Long count, int depth) {
        String productKey = batch.getProductKey();
        if (depth > 0) {
            productGraph.computeIfAbsent(productKey, (key)
                    -> productGraph.put(key, batch.getAmount() * count));
            productGraph.computeIfPresent(productKey, (key, amount)
                    -> productGraph.put(key, amount + batch.getAmount() * count));
        }

        Map<String, ProductionConfig> productionConfigMap = farm.getProductionConfigMap();

        ProductionConfig productionConfig = productionConfigMap.get(productKey);
        Factory factory = farm.getProductToFactoryMap().get(productKey);
        Duration productionDuration = productionConfig.getProductionDuration();

        productionFactoryGraph.computeIfPresent(factory, (key, production)
                -> productionFactoryGraph.get(key).addProducts(batch, count, productionDuration));
        productionFactoryGraph.computeIfAbsent(factory, (key)
                -> productionFactoryGraph.put(key, new Production(batch, count, factory, productionDuration)));

        productionTimeGraph.put(factory, strategy.recalculate(productionFactoryGraph.get(factory)));

        if (farm.isZeroFactoryProduct(productKey)) {
            return;
        }

        Collection<ProductBatch> products = productionConfig.getInput().getProducts();

        for (ProductBatch productBatch : products) {
            calculate(productBatch, batch.getAmount() * count, depth + 1);
        }
    }

}
