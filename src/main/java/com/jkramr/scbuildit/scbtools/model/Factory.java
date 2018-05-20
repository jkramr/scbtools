package com.jkramr.scbuildit.scbtools.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Factory {

    @NonNull
    private String name;
    @NonNull
    private Integer totalSlots;
    @NonNull
    private Integer concurrentOutput;
}
