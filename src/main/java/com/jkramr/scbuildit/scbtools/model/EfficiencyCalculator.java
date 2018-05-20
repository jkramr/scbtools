package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class EfficiencyCalculator {

    @NonNull
    private Farm farm;
    @NonNull
    private Map<String, Duration> totalProductionTimeMap;
    @NonNull
    private Map<String, Double> efficiencyMap;

    public EfficiencyCalculator(Farm farm) {
        this.farm = farm;
        this.efficiencyMap = new HashMap<>();
        this.totalProductionTimeMap = new HashMap<>();
    }

    public void calculate(String... products) {
        Collection<String> productSet = Arrays.asList(products);
        if (products.length <= 0) {
            productSet = farm.productToFactoryMap.keySet();
        }
        for (String product : productSet) {
            calculate(new ProductBatch(product, 1), 0);
        }

        totalProductionTimeMap.forEach((product, totalDuration) -> {
            ProductionConfig productionConfig = farm.getProductionConfigMap().get(product);
            efficiencyMap.put(product, productionConfig.getPrice() * productionConfig.getOutput().getAmount() / totalDuration.toMinutes());
        });
    }

    private void calculate(ProductBatch product, int depth) {
        String productKey = product.getProductKey();
        Map<String, ProductionConfig> productionConfigMap = farm.getProductionConfigMap();

        ProductionConfig productionConfig = productionConfigMap.get(productKey);
        Duration productionDuration = productionConfig.getProductionDuration();
        totalProductionTimeMap.computeIfAbsent(productKey, (key) -> totalProductionTimeMap.put(key, productionDuration));
        totalProductionTimeMap.computeIfPresent(productKey, (key, duration) -> totalProductionTimeMap.put(key, duration.plus(productionDuration)));

        if (farm.isZeroFactoryProduct(productKey)) {
            return;
        }

        Collection<ProductBatch> products = productionConfig.getInput().getProducts();

        for (ProductBatch productBatch : products) {
            calculate(productBatch, depth + 1);
        }
    }

}
