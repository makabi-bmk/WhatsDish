package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
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
    private ArrayList<String> matVar = new ArrayList<>();
    private HashMap<String, ArrayList<String>> cannotEatIngredientList = new HashMap<>();

    DishData(int ID, Context context) {
        this.ID = ID;
        this.mContext = context;
        initData();
        setCannotEatIgredientList();
    }

    private void initData() {
        //TODO あとで写真、沖縄そば以外にも対応させる
        picture = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.okinawasoba);
        int strID;

        strID = mContext.getResources().getIdentifier("food_name_" + ID, "string", mContext.getPackageName());
        name = mContext.getResources().getString(strID);

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
            matVar.add(mContext.getResources().getString(strID));

            //表示用の材料名を格納
            resName = "fooddata_mat_" + ID + "_" + i;
            strID = mContext.getResources().getIdentifier(resName, "string", mContext.getPackageName());
            if (strID == 0) {
                break;
            }
            mat.add(mContext.getResources().getString(strID));
            i++;
        }
    }

    void setCannotEatIgredientList() {

        int i = 0;
        int matLength = matVar.size();
        for(int j = 0; j < matLength; j++) {
            String name = matVar.get(j);
            String matName = mat.get(j);
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
                     strID = mContext.getResources().getIdentifier(ingredientFile.getResName(ingredientID), "string", mContext.getPackageName());
                     Log.d("weiwei", mContext.getString(strID) + "は食べられません！");
                     canEat = false;
                     if (!cannotEatIngredientList.containsKey(matName)) {
                         cannotEatIngredientList.put(matName, new ArrayList<String>());
                     }
                     cannotEatIngredientList.get(matName).add(ingredientFile.getIngredientData(ingredientID).name);
                 } else {
                     strID = mContext.getResources().getIdentifier(ingredientFile.getResName(ingredientID), "string", mContext.getPackageName());
                     Log.d("weiwei", mContext.getString(strID) + "は食べれる！");
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

    ArrayList<String> getMat() {
        return mat;
    }

    public boolean isCanEat() {
        return canEat;
    }

    public Bitmap getPicture() {
        return picture;
    }
}
