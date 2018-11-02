package com.example.shirokuma.whatsdish;

public class IngredientListFormat {
    //public int listID;
    public String ingredientName;
    public boolean isPossibleToEat;
    Category category;

    IngredientListFormat(int listID, String ingredientName, Category category, boolean isPossibleToEat) {
        //this.listID = listID;
        this.ingredientName = ingredientName;
        this.isPossibleToEat = isPossibleToEat;
        this.category = category;
    }

    IngredientListFormat(int listID, String ingredientName, Category category) {
        //this.listID = listID;
        this.ingredientName = ingredientName;
        this.isPossibleToEat = true;
        this.category = category;
    }

    public int getListID() {
        //return listID;
        return 0;
    }

    public String getName() {
        return ingredientName;
    }

    public boolean isPossibleToEat() {
        return isPossibleToEat;
    }

    public enum Category {
        vegetable,
        fruit,
        meat,
        seafood,
        seasoning,
        grain,
        dairy_product
    };

}
