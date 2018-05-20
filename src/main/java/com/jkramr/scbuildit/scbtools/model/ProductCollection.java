package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Data
public class ProductCollection {
    @NonNull
    private Collection<ProductBatch> products;

    public ProductCollection(ProductBatch[] products) {
        this.products = Arrays.asList(products);
    }

    public ProductCollection(ProductBatch productBatch) {
        this.products = Collections.singleton(productBatch);
    }
}
