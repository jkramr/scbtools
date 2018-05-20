package com.jkramr.scbuildit.scbtools.farms;

import com.jkramr.scbuildit.scbtools.model.Factory;
import com.jkramr.scbuildit.scbtools.model.Farm;
import com.jkramr.scbuildit.scbtools.model.ProductBatch;

import java.time.Duration;
import java.util.HashMap;

public class HayDayFarm extends Farm {

    public static final String FACTORY_FIELD = "Field";
    public static final String FACTORY_FEED_MILL = "Feed Mill";
    // Animals
    public static final String FACTORY_CHICKEN_COOP = "Chicken Coop";
    public static final String FACTORY_COW_PASTURE = "Cow Pasture";
    public static final String FACTORY_PIG_PEN = "Pig Pen";
    public static final String FACTORY_SHEEP_PASTURE = "Sheep Pasture";
    // Production
    public static final String FACTORY_BAKERY = "Bakery";

    public static final String PRODUCT_WHEAT = "Wheat";
    public static final String PRODUCT_CORN = "Corn";
    public static final String PRODUCT_CARROT = "Carrot";
    public static final String PRODUCT_SOYBEAN = "Soybean";

    public static final String PRODUCT_CHICKEN_FOOD = "Chicken Food";
    public static final String PRODUCT_COW_FOOD = "Cow Food";
    public static final String PRODUCT_PIG_FOOD = "Pig Food";
    public static final String PRODUCT_SHEEP_FOOD = "Sheep Food";

    public static final String PRODUCT_EGGS = "Eggs";
    public static final String PRODUCT_MILK = "Milk";
    public static final String PRODUCT_BACON = "Bacon";
    public static final String PRODUCT_WOOL = "Wool";

    public static final String PRODUCT_BREAD = "Bread";
    public static final String PRODUCT_CORN_BREAD = "Corn Bread";


    private HashMap<String, String> dictionary;


    public HayDayFarm(Integer fieldSlots,
                      Integer feedMillSlots,
                      Integer chickenCoopSlots,
                      Integer cowPastureSlots,
                      Integer pigPenSlots,
                      Integer sheepPastureSlots,
                      Integer bakerySlots
    ) {
        super();
        dictionary = new HashMap<>();

        // Field
        Factory fieldFactory = new Factory(FACTORY_FIELD, fieldSlots);
        fieldFactory.setConcurrentOutput(fieldSlots);
        zeroFactory = fieldFactory;

        registerProduct(fieldFactory, PRODUCT_WHEAT, Duration.ofMinutes(2), 3.0, 2,
                new ProductBatch(PRODUCT_WHEAT, 1));
        registerProduct(fieldFactory, PRODUCT_CORN, Duration.ofMinutes(5), 7.0, 2,
                new ProductBatch(PRODUCT_CORN, 1));
        registerProduct(fieldFactory, PRODUCT_CARROT, Duration.ofMinutes(10), 7.0, 2,
                new ProductBatch(PRODUCT_SOYBEAN, 1));
        registerProduct(fieldFactory, PRODUCT_SOYBEAN, Duration.ofMinutes(20), 10.0, 2,
                new ProductBatch(PRODUCT_SOYBEAN, 1));


        // Feed Mill
        Factory feedMillFactory = new Factory(FACTORY_FEED_MILL, feedMillSlots);

        registerProduct(feedMillFactory, PRODUCT_CHICKEN_FOOD, Duration.ofMinutes(5), 7.0, 3,
                new ProductBatch(PRODUCT_WHEAT, 2),
                new ProductBatch(PRODUCT_CORN, 1));

        registerProduct(feedMillFactory, PRODUCT_COW_FOOD, Duration.ofMinutes(10), 14.0, 3,
                new ProductBatch(PRODUCT_CORN, 2),
                new ProductBatch(PRODUCT_SOYBEAN, 1));

        registerProduct(feedMillFactory, PRODUCT_PIG_FOOD, Duration.ofMinutes(20), 14.0, 3,
                new ProductBatch(PRODUCT_CARROT, 2),
                new ProductBatch(PRODUCT_SOYBEAN, 1));

        registerProduct(feedMillFactory, PRODUCT_SHEEP_FOOD, Duration.ofMinutes(30), 14.0, 3,
                new ProductBatch(PRODUCT_WHEAT, 3),
                new ProductBatch(PRODUCT_SOYBEAN, 1));

        // Chicken Coop
        Factory chickenCoopFactory = new Factory(FACTORY_CHICKEN_COOP, chickenCoopSlots);
        chickenCoopFactory.setConcurrentOutput(chickenCoopSlots);
        chickenCoopFactory.setSingleProduct(true);

        registerProduct(chickenCoopFactory, PRODUCT_EGGS, Duration.ofMinutes(20), 18.0, 1,
                new ProductBatch(PRODUCT_CHICKEN_FOOD, 1));

        // Cow Pasture
        Factory cowPastureFactory = new Factory(FACTORY_COW_PASTURE, cowPastureSlots);
        cowPastureFactory.setConcurrentOutput(cowPastureSlots);
        cowPastureFactory.setSingleProduct(true);

        registerProduct(cowPastureFactory, PRODUCT_MILK, Duration.ofHours(1), 32.0, 1,
                new ProductBatch(PRODUCT_COW_FOOD, 1));

        // Pig Pen
        Factory pigPenFactory = new Factory(FACTORY_PIG_PEN, pigPenSlots);
        pigPenFactory.setConcurrentOutput(pigPenSlots);
        pigPenFactory.setSingleProduct(true);

        registerProduct(pigPenFactory, PRODUCT_BACON, Duration.ofHours(4), 50.0, 1,
                new ProductBatch(PRODUCT_PIG_FOOD, 1));

        // Sheep Pasture

        Factory sheepPastureFactory = new Factory(FACTORY_SHEEP_PASTURE, sheepPastureSlots);
        sheepPastureFactory.setConcurrentOutput(sheepPastureSlots);
        sheepPastureFactory.setSingleProduct(true);

        registerProduct(sheepPastureFactory, PRODUCT_WOOL, Duration.ofHours(6), 54.0, 1,
                new ProductBatch(PRODUCT_SHEEP_FOOD, 1));

        // Bakery
        Factory bakeryFactory = new Factory(FACTORY_BAKERY, bakerySlots);

        registerProduct(bakeryFactory, PRODUCT_BREAD, Duration.ofMinutes(5), 21.0, 1,
                new ProductBatch(PRODUCT_WHEAT, 3));
        registerProduct(bakeryFactory, PRODUCT_CORN_BREAD, Duration.ofMinutes(30), 72.0, 1,
                new ProductBatch(PRODUCT_CORN, 2),
                new ProductBatch(PRODUCT_EGGS, 2));
    }

    private void registerProduct(Factory factory, String productKey, Duration duration, Double maxPrice, Integer outputAmount, ProductBatch... inputProducts) {
        registerProductConfig(productKey, outputAmount, duration, maxPrice, inputProducts);
        registerProductToFactory(productKey, factory);
    }

}
