package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.shirokuma.whatsdish.MainActivity.ingredientFile;
import static java.sql.Types.NULL;

public class FoodData2 {
    private int ID;
    private String language;
    private String foodName;
    private ArrayList<String> ingredientDetail = new ArrayList<>();
    private String detail;
    Bitmap picture;
    private Context context;
    boolean canEat = true;

    HashMap<String, ArrayList<String>> cannotEatIngredientList = new HashMap<>();

    FoodData2(int ID, String language, Context context) {
        this.ID = ID;
        this.language = language;
        this.context = context;
        initData();
        setCannotEatIgredientList();
    }

    void initData() {
//        bitmap = BitmapFactory.
        int strID;
        strID = context.getResources().getIdentifier("food_name_" + language + "_" + ID, "string", context.getPackageName());
        foodName = context.getResources().getString(strID);
        strID = context.getResources().getIdentifier("food_detail_" + language + "_" + ID, "string", context.getPackageName());
        detail = context.getResources().getString(strID);

        int i = 0;
        while (true) {
            String strName = "food_ingredient_detail_" + language + "_" + ID + "_" + i;
            strID = context.getResources().getIdentifier(strName, "string", context.getPackageName());
            if (strID == NULL || strID == 0) {
                break;
            }
            ingredientDetail.add(context.getString(strID));
            i++;
        }
    }

    void setCannotEatIgredientList() {

        int i = 0;
        for(String name : ingredientDetail) {
            //材料に含まれる食材の情報を呼び出す
            Log.d("weiwei", "材料名 = " + name);
            int strID = context.getResources().getIdentifier(name + "_" + i, "string", context.getPackageName());
            if (strID == NULL || strID == 0) {
                break;
            }
            int ingredientID = Integer.valueOf(context.getString(strID));

            //食材が食べられないものリストとして選択されていないか確かめる
            if (ingredientID == -1) {
                Log.d("weiwei", "食材リストにその食材はないYO");
                continue;
            } else {
                 if (ingredientFile.getIngredientData(ingredientID).isSelect) {
                     Log.d("weiwei", ingredientFile.getIngredientData(ingredientID).name + "は食べられません！");
                     canEat = false;
                     if (!cannotEatIngredientList.containsKey(name)) {
                         cannotEatIngredientList.put(name, new ArrayList<String>());
                     }
                     cannotEatIngredientList.get(name).add(ingredientFile.getIngredientData(ingredientID).name);
                 } else {
                     Log.d("weiwei", ingredientFile.getIngredientData(ingredientID).name + "は食べれる！");
                 }
            }
            i++;
        }
    }

    public String getFoodName() {
        return foodName;
    }

    public int getID() {
        return ID;
    }

    public String getDetail() {
        return detail;
    }

    public ArrayList<String> getIngredientDetail() {
        return ingredientDetail;
    }
}
