package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.example.shirokuma.whatsdish.MainActivity.ingredientFile;

public class DishData {

    private int ID;
    private String name;
    private String detail;

    private Bitmap picture;
    private Context mContext;
    private boolean canEat = true;

    private ArrayList<String> mat = new ArrayList<>();
    private ArrayList<String> foodMatDetailKeys = new ArrayList<>();
    private HashMap<String, ArrayList<String>> cannotEatIngredientList = new HashMap<>();
    private HashMap<String, ArrayList<Integer>> foodMatDetail = new HashMap<String, ArrayList<Integer>>();

    DishData(int ID, Context context) {
        this.ID = ID;
        this.mContext = context;
        initData();
        setCannotEatIngredientList();
    }

    private void initData() {
        int strID;

        //料理名の設定
        strID = mContext.getResources().getIdentifier("food_name_" + ID, "string", mContext.getPackageName());
        name = mContext.getResources().getString(strID);

        //料理の詳細の設定
        strID = mContext.getResources().getIdentifier("food_detail_" + ID, "string", mContext.getPackageName());
        detail = mContext.getResources().getString(strID);

        int i = 0;
        while (true) {
            //内部処理に使っているxml,多言語対応の必要なし
            String resName = "fooddata_mat_var_" + ID + "_" + i;
            strID = mContext.getResources().getIdentifier(resName, "string", mContext.getPackageName());
            if (strID == 0) {
                break;
            }
            foodMatDetailKeys.add(mContext.getResources().getString(strID));

            //表示用の材料名を設定
            resName = "fooddata_mat_" + ID + "_" + i;
            strID = mContext.getResources().getIdentifier(resName, "string", mContext.getPackageName());
            if (strID == 0) {
                break;
            }
            mat.add(mContext.getResources().getString(strID));
            i++;
        }
    }

    void setBitmap() {
        int strID;

        //写真の設定
        strID = mContext.getResources().getIdentifier("food_" + ID, "drawable", mContext.getPackageName());
        picture = BitmapFactory.decodeResource(mContext.getResources(), strID);
    }

    void setCannotEatIngredientList() {

        int matLength = foodMatDetailKeys.size();
        Log.d("weiwei", "foodMatDetailKeys = " + foodMatDetailKeys + ", length = " + matLength);
        initFoodMatDetail();

        for (int i = 0; i < matLength; i++) {
            String foodMatDetailKey = foodMatDetailKeys.get(i);
            String matName = mat.get(i);
            
            if (foodMatDetail.containsKey(foodMatDetailKey)) {
                ArrayList<Integer> foodMatDetailList = foodMatDetail.get(foodMatDetailKey);
                for (int ingredientID : foodMatDetailList) {

                    String cannotIngredientName = ingredientFile.getIngredientName(ingredientID);
                    Log.d("weiwei", "使われている食材 = " + cannotIngredientName + ", ");

                    if (ingredientFile.getIngredientData(ingredientID).isSelect) {
                        Log.d("weiwei", matName + "は食べられません！");
                        canEat = false;
                        if (!cannotEatIngredientList.containsKey(matName)) {
                            cannotEatIngredientList.put(matName, new ArrayList<String>());
                        }
                        cannotEatIngredientList.get(matName).add(cannotIngredientName);

                    } else {
                        Log.d("weiwei", matName + "は食べれる！");
                    }
                }
            }
        }
    }

    private void initFoodMatDetail() {
        foodMatDetail.put("豆腐", new ArrayList<>(Arrays.asList(177)));
        foodMatDetail.put("魚", new ArrayList<>(Arrays.asList(122)));
        foodMatDetail.put("塩", new ArrayList<>(Arrays.asList(146)));
        foodMatDetail.put("醤油", new ArrayList<>(Arrays.asList(146, 177, 173)));
        foodMatDetail.put("イカ", new ArrayList<>(Arrays.asList(126)));
        foodMatDetail.put("豚", new ArrayList<>(Arrays.asList(112)));
        foodMatDetail.put("しいたけ", new ArrayList<>(Arrays.asList(13)));
        foodMatDetail.put("カステラかまぼこ", new ArrayList<>(Arrays.asList(192, 122)));
        foodMatDetail.put("味噌", new ArrayList<>(Arrays.asList(177, 173, 146)));
        foodMatDetail.put("かまぼこ", new ArrayList<>(Arrays.asList(122, 145, 146, 126)));
        foodMatDetail.put("大豆", new ArrayList<>(Arrays.asList(175)));
        foodMatDetail.put("にんじん", new ArrayList<>(Arrays.asList(48)));
        foodMatDetail.put("酒", new ArrayList<>(Arrays.asList(170)));
        foodMatDetail.put("みりん", new ArrayList<>(Arrays.asList(170)));
        foodMatDetail.put("ゴーヤ", new ArrayList<>(Arrays.asList(53)));
        foodMatDetail.put("卵", new ArrayList<>(Arrays.asList(192)));
        foodMatDetail.put("胡椒", new ArrayList<>(Arrays.asList(157)));
        foodMatDetail.put("落花生", new ArrayList<>(Arrays.asList(179)));
        foodMatDetail.put("砂糖", new ArrayList<>(Arrays.asList(145)));
        foodMatDetail.put("米", new ArrayList<>(Arrays.asList(171)));
        foodMatDetail.put("レタス", new ArrayList<>(Arrays.asList(40)));
        foodMatDetail.put("トマト", new ArrayList<>(Arrays.asList(24)));
        foodMatDetail.put("チーズ", new ArrayList<>(Arrays.asList(191)));
        foodMatDetail.put("合いびき肉", new ArrayList<>(Arrays.asList(112, 111)));
        foodMatDetail.put("にんにく", new ArrayList<>(Arrays.asList(29)));
        foodMatDetail.put("クミン", new ArrayList<>(Arrays.asList(166)));
        foodMatDetail.put("ヘチマ", new ArrayList<>(Arrays.asList(60)));
        foodMatDetail.put("パパイヤ", new ArrayList<>(Arrays.asList(103)));
        foodMatDetail.put("にら", new ArrayList<>(Arrays.asList(28)));
        foodMatDetail.put("小麦", new ArrayList<>(Arrays.asList(173)));
        foodMatDetail.put("ゴマ", new ArrayList<>(Arrays.asList(187)));
        foodMatDetail.put("ピーナッツバター", new ArrayList<>(Arrays.asList(177, 146, 193)));
        foodMatDetail.put("酢", new ArrayList<>(Arrays.asList(147)));
        foodMatDetail.put("ヤギ", new ArrayList<>(Arrays.asList(115)));
        foodMatDetail.put("しょうが", new ArrayList<>(Arrays.asList(141)));
        foodMatDetail.put("じゃがいも", new ArrayList<>(Arrays.asList(16)));
        foodMatDetail.put("とうもろこし", new ArrayList<>(Arrays.asList(23)));
        foodMatDetail.put("たけのこ", new ArrayList<>(Arrays.asList(22)));
        foodMatDetail.put("からし菜", new ArrayList<>(Arrays.asList(61)));
        foodMatDetail.put("鶏", new ArrayList<>(Arrays.asList(113)));
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

    ArrayList<String> getMat() {
        return mat;
    }

    public boolean isCanEat() {
        return canEat;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public HashMap<String, ArrayList<String>> getCannotEatIngredientList() {
        return cannotEatIngredientList;
    }
}
