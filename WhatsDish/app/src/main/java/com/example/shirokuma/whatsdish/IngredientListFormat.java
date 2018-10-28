package com.example.shirokuma.whatsdish;

public class IngredientListFormat {
    public String ingredientName;
    public boolean isPossibleToEat;
    Category category;

    IngredientListFormat(String ingredientName, Category category, boolean isPossibleToEat) {

        this.ingredientName = ingredientName;
        this.isPossibleToEat = isPossibleToEat;
        this.category = category;
    }

    IngredientListFormat(String ingredientName, Category category) {
        this.ingredientName = ingredientName;
        this.isPossibleToEat = true;
        this.category = category;
    }

    public static enum Category {
        vegetable,
        fruit,
        meat,
        seafood,
        seasoning,
        grain,
        dairy_product
    };

}
