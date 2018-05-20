package com.jkramr.scbuildit.scbtools.model;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleNoConcurrentProductionStrategy implements ProductionStrategy {
    @Override
    public Duration recalculate(Production production) {
        AtomicReference<Duration> duration = new AtomicReference<>(Duration.ofMillis(0));
        production.getBatches().forEach((batch, count) -> {
            Duration batchDuration = production.getConfig().get(batch);
            duration.getAndUpdate((value) -> value.plus(batchDuration.multipliedBy(count)));
        });
        return duration.get();
    }
}
