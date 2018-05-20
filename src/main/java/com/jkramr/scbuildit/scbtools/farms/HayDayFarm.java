package com.jkramr.scbuildit.scbtools.farms;

import com.jkramr.scbuildit.scbtools.model.Factory;
import com.jkramr.scbuildit.scbtools.model.Farm;
import com.jkramr.scbuildit.scbtools.model.ProductBatch;

import java.time.Duration;
import java.util.HashMap;

public class HayDayFarm extends Farm {

    public static final String FACTORY_FIELD = "factory_field";
    public static final String FACTORY_FEED_MILL = "factory_feed_mill";
    public static final String FACTORY_CHICKEN_COOP = "factory_chicken_coop";

    public static final String PRODUCT_WHEAT = "product_wheat";
    public static final String PRODUCT_CORN = "product_corn";
    public static final String PRODUCT_SOYBEAN = "product_soybean";
    public static final String PRODUCT_COW_FOOD = "product_cow_food";
    public static final String PRODUCT_CHICKEN_FOOD = "product_chicken_food";
    public static final String PRODUCT_EGGS = "product_eggs";
    public static final String FACTORY_COW_PASTURE = "factory_cow_pasture";
    public static final String PRODUCT_MILK = "product_milk";

    private HashMap<String, String> dictionary;


    public HayDayFarm(Integer fieldSlots,
                      Integer feedMillSlots,
                      Integer chickenCoopSlots,
                      Integer cowPastureSlots) {
        super();
        dictionary = new HashMap<>();

        // Field
        dictionary.put(FACTORY_FIELD, "Field");

        Factory fieldFactory = new Factory(FACTORY_FIELD, fieldSlots);
        fieldFactory.setConcurrentOutput(fieldSlots);
        zeroFactory = fieldFactory;

        registerProduct(fieldFactory, PRODUCT_WHEAT, "Wheat",Duration.ofMinutes(2), 2,
                new ProductBatch(PRODUCT_WHEAT, 1));

        registerProduct(fieldFactory, PRODUCT_CORN, "Corn", Duration.ofMinutes(5), 2,
                new ProductBatch(PRODUCT_CORN, 1));

        registerProduct(fieldFactory, PRODUCT_SOYBEAN, "Soybean", Duration.ofMinutes(20), 2,
                new ProductBatch(PRODUCT_SOYBEAN, 1));


        // Feed Mill
        dictionary.put(FACTORY_FEED_MILL, "Feed Mill");

        Factory feedMillFactory = new Factory(FACTORY_FEED_MILL, feedMillSlots);

        registerProduct(feedMillFactory, PRODUCT_CHICKEN_FOOD, "Chicken Food", Duration.ofMinutes(10), 3,
                new ProductBatch(PRODUCT_WHEAT, 2),
                new ProductBatch(PRODUCT_CORN, 1));

        registerProduct(feedMillFactory, PRODUCT_COW_FOOD, "Cow Food", Duration.ofMinutes(10), 3,
                new ProductBatch(PRODUCT_CORN, 2),
                new ProductBatch(PRODUCT_SOYBEAN, 1)
        );

        // Chicken Coup
        dictionary.put(FACTORY_CHICKEN_COOP, "Chicken Coop");

        Factory chickenCoopFactory = new Factory(FACTORY_CHICKEN_COOP, chickenCoopSlots);
        chickenCoopFactory.setConcurrentOutput(chickenCoopSlots);
        chickenCoopFactory.setSingleProduct(true);

        registerProduct(chickenCoopFactory, PRODUCT_EGGS, "Eggs", Duration.ofMinutes(20), 1,
                new ProductBatch(PRODUCT_CHICKEN_FOOD, 1));

        // Cow Pasture
        dictionary.put(FACTORY_COW_PASTURE, "Cow Pasture");

        Factory cowPastureFactory = new Factory(FACTORY_COW_PASTURE, cowPastureSlots);
        cowPastureFactory.setConcurrentOutput(cowPastureSlots);
        cowPastureFactory.setSingleProduct(true);

        registerProduct(cowPastureFactory, PRODUCT_MILK, "Milk", Duration.ofHours(1), 1,
                new ProductBatch(PRODUCT_COW_FOOD, 1));
    }

    private void registerProduct(Factory factory, String productKey, String productName, Duration duration, Integer outputAmount, ProductBatch... inputProducts) {
        dictionary.put(productKey, productName);
        registerProductConfig(productKey, outputAmount, duration, inputProducts);
        registerProductToFactory(productKey, factory);
    }

}
