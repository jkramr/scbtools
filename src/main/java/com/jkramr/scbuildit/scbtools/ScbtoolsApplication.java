package com.jkramr.scbuildit.scbtools;

import com.jkramr.scbuildit.scbtools.farms.HayDayFarm;
import com.jkramr.scbuildit.scbtools.model.ProductBatch;
import com.jkramr.scbuildit.scbtools.model.ProductionCalculator;
import com.jkramr.scbuildit.scbtools.model.SimpleNoConcurrentProductionStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScbtoolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScbtoolsApplication.class, args);

		HayDayFarm hayDayFarm = new HayDayFarm(42, 18, 18, 10);

		ProductionCalculator calculator = new ProductionCalculator(hayDayFarm, new SimpleNoConcurrentProductionStrategy());

		calculator.calculate(new ProductBatch(HayDayFarm.PRODUCT_MILK, 3), new ProductBatch(HayDayFarm.PRODUCT_EGGS, 10));

		System.out.println(calculator.getProductGraph());
		System.out.println(calculator.getProductionTimeGraph());
	}
}
