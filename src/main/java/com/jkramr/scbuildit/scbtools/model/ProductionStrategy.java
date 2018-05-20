package com.jkramr.scbuildit.scbtools.model;

import java.time.Duration;

public interface ProductionStrategy {
    Duration recalculate(Production production);
}
