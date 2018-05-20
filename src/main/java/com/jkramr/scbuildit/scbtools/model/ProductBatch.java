package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class ProductBatch {
    @NonNull
    private String productKey;
    @NonNull
    private Integer amount;
}
