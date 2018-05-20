package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Factory {

    @NonNull
    private String name;
    @NonNull
    private Integer totalSlots;
    private Integer concurrentOutput = 1;
    private boolean isSingleProduct = false;
}
