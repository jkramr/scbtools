package com.jkramr.scbuildit.scbtools;

import com.jkramr.scbuildit.scbtools.farms.HayDayFarm;
import com.jkramr.scbuildit.scbtools.model.EfficiencyCalculator;
import com.jkramr.scbuildit.scbtools.model.ProductBatch;
import com.jkramr.scbuildit.scbtools.model.ProductionCalculator;
import com.jkramr.scbuildit.scbtools.model.SimpleProductionStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ScbtoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScbtoolsApplication.class, args);

        HayDayFarm hayDayFarm = new HayDayFarm(
                42,
                18,
                18,
                10,
                10,
                5,
                9
        );

        ProductionCalculator calculator = new ProductionCalculator(hayDayFarm, new SimpleProductionStrategy());
        EfficiencyCalculator efficiencyCalculator= new EfficiencyCalculator(hayDayFarm);

        ProductBatch[] neededProducts = {
                new ProductBatch(HayDayFarm.PRODUCT_MILK, 30),
                new ProductBatch(HayDayFarm.PRODUCT_EGGS, 20),
                new ProductBatch(HayDayFarm.PRODUCT_CORN_BREAD, 5)
        };

        System.out.println("Needed: " + Arrays.toString(neededProducts));
        calculator.calculate(neededProducts);
        efficiencyCalculator.calculate();

        System.out.println(calculator.getProductGraph());
        System.out.println(calculator.getProductionFactoryGraph());
        System.out.println(calculator.getProductionTimeGraph());

        System.out.println("Efficiency: " + efficiencyCalculator.getEfficiencyMap());
    }
}
