package com.example.shirokuma.whatsdish;

public class IngredientListFormat {
    public String name;
    public boolean isSelect;
    Category category;

    public enum Category {
        vegetable,
        fruit,
        meat,
        seafood,
        seasoning,
        grain,
        dairy_product
    };

    static String[] categoryNames = {"vegetables", "fruits", "meats", "seafoods", "seasonings", "grains", "dairy_products"};
    static Category[] categories = {IngredientListFormat.Category.vegetable, IngredientListFormat.Category.fruit, IngredientListFormat.Category.meat,
            IngredientListFormat.Category.seafood, IngredientListFormat.Category.seasoning, IngredientListFormat.Category.seasoning,
            IngredientListFormat.Category.grain, IngredientListFormat.Category.dairy_product};

    IngredientListFormat(String name, Category category, boolean isSelect) {
        this.name = name;
        this.isSelect = isSelect;
        this.category = category;
    }

    IngredientListFormat(String name, Category category) {
        this.name = name;
        this.isSelect = false;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public Category getCategory() {
        return category;
    }
}
