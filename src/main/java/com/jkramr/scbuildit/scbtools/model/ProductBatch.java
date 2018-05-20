package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(includeFieldNames = false)
public class ProductBatch {
    @NonNull
    private String productKey;
    @NonNull
    private Integer amount;
}
