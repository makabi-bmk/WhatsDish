package com.example.shirokuma.whatsdish;

public class IngredientData extends Data{
    public enum Category {
        vegetable,
        fruit,
        meat,
        seafood,
        seasoning,
        grain,
        dairy_product
    };

    Category category;

    static String[] categoryNames = {"vegetables", "fruits", "meats", "seafoods", "seasonings", "grains", "dairy_products"};
    static Category[] categories = {IngredientData.Category.vegetable, IngredientData.Category.fruit, IngredientData.Category.meat,
            IngredientData.Category.seafood, IngredientData.Category.seasoning, IngredientData.Category.grain,
            IngredientData.Category.dairy_product};

    IngredientData(String name, Category category, boolean isSelect) {
        super(name, isSelect);
        this.category = category;
    }

    IngredientData(String name, Category category) {
        super(name);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
