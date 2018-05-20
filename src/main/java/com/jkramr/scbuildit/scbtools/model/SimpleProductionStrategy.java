package com.jkramr.scbuildit.scbtools.model;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleProductionStrategy implements ProductionStrategy {
    @Override
    public Duration recalculate(Production production) {
        Factory factory = production.getFactory();
        Integer concurrentOutput = factory.getConcurrentOutput();
        if (factory.isSingleProduct() && concurrentOutput > 1) {
            AtomicReference<Long> totalProducts = new AtomicReference<>(0L);
            AtomicReference<Duration> singleProductDuration = new AtomicReference<>(null);
            production.getBatches().forEach((batch, count) -> {
                totalProducts.updateAndGet(value -> value + count);
                singleProductDuration.set(production.getConfig().get(batch));
            });
            long concurrentBatches = totalProducts.get() / concurrentOutput;
            return singleProductDuration.get().multipliedBy(concurrentBatches > 1 ? concurrentBatches : 1);
        } else {
            AtomicReference<Duration> duration = new AtomicReference<>(Duration.ofMillis(0));
            production.getBatches().forEach((batch, count) -> {
                Duration batchDuration = production.getConfig().get(batch);
                duration.getAndUpdate((value) -> value.plus(batchDuration.multipliedBy(count)));
            });
            return duration.get();
        }
    }
}
