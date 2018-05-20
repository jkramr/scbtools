package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(includeFieldNames = false, of = {"name"})
public class Factory {

    @NonNull
    private String name;
    @NonNull
    private Integer totalSlots;
    private Integer concurrentOutput = 1;
    private boolean isSingleProduct = false;
}
