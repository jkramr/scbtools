package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Farm {
    @NonNull
    protected Map<String, ProductionConfig> productionConfigMap;
    @NonNull
    protected Map<String, Factory> productToFactoryMap;
    @NonNull
    protected Factory zeroFactory;

    public Farm() {
        this.productionConfigMap = new HashMap<>();
        this.productToFactoryMap = new HashMap<>();
    }

    public ProductionConfig registerProductConfig(String product, Integer outputAmount, Duration productinoDuration, ProductBatch... productInput) {
        ProductionConfig productionConfig = new ProductionConfig(new ProductCollection(productInput), productinoDuration, new ProductCollection(new ProductBatch(product, outputAmount)));
        productionConfigMap.put(product, productionConfig);
        return productionConfig;
    }

    public void registerProductToFactory(String product, Factory factory) {
        productToFactoryMap.put(product, factory);
    }

    public boolean isZeroFactoryProduct(String productKey) {
        return productToFactoryMap.get(productKey).equals(zeroFactory);
    }
}
