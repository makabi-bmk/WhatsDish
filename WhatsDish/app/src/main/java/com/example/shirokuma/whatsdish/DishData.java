package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.shirokuma.whatsdish.MainActivity.ingredientFile;
import static java.sql.Types.NULL;

public class DishData {

    private int ID;
    private String language;
    private String name;
    private String detail;

    private Bitmap picture;
    private Context mContext;
    private boolean canEat = true;

    private ArrayList<String> ingredientDetail = new ArrayList<>();
    private HashMap<String, ArrayList<String>> cannotEatIngredientList = new HashMap<>();

    DishData(int ID, String language, Context context) {
        this.ID = ID;
        this.language = language;
        this.mContext = context;
        initData();
        setCannotEatIgredientList();
    }

    private void initData() {
        //TODO あとで写真、沖縄そば以外にも対応させる
        picture = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.okinawasoba);
        int strID;

        strID = mContext.getResources().getIdentifier("food_name_" + language + "_" + ID, "string", mContext.getPackageName());
        name = mContext.getResources().getString(strID);

        strID = mContext.getResources().getIdentifier("food_detail_" + language + "_" + ID, "string", mContext.getPackageName());
        detail = mContext.getResources().getString(strID);

        int i = 0;
        while (true) {
            String resName = "food_ingredient_detail_" + language + "_" + ID + "_" + i;
            strID = mContext.getResources().getIdentifier(resName, "string", mContext.getPackageName());
            if (strID == 0) {
                break;
            }
            ingredientDetail.add(mContext.getString(strID));
            i++;
        }
    }

    void setCannotEatIgredientList() {

        int i = 0;
        for(String name : ingredientDetail) {
            //材料に含まれる食材の情報を呼び出す
            int strID = mContext.getResources().getIdentifier(name + "_" + i, "string", mContext.getPackageName());
            if (strID == 0) {
                break;
            }
            int ingredientID = Integer.valueOf(mContext.getString(strID));

            //食材が食べられないものリストとして選択されていないか確かめる
            if (ingredientID == -1) {
                Log.d("weiwei", "食材リストにその食材はないYO");
                continue;
            } else {
                Log.d("weiwei", "ingredientID =" +  ingredientID);
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

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    String getDetail() {
        return detail;
    }

    ArrayList<String> getIngredientDetail() {
        return ingredientDetail;
    }

    public boolean isCanEat() {
        return canEat;
    }

    public Bitmap getPicture() {
        return picture;
    }
}
