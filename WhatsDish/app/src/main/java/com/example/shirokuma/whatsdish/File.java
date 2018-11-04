package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.shirokuma.whatsdish.IngredientData.Category.dairy_product;
import static com.example.shirokuma.whatsdish.IngredientData.Category.fruit;
import static com.example.shirokuma.whatsdish.IngredientData.Category.grain;
import static com.example.shirokuma.whatsdish.IngredientData.Category.meat;
import static com.example.shirokuma.whatsdish.IngredientData.Category.seafood;
import static com.example.shirokuma.whatsdish.IngredientData.Category.seasoning;
import static com.example.shirokuma.whatsdish.IngredientData.Category.vegetable;
import static java.sql.Types.NULL;

public class File {

    String fileName = null;
    private String jsonData = null;
    private ArrayList<Data> list = new ArrayList<>();
    private ArrayList<IngredientData> ingredientList = new ArrayList<>();
    private Context context = null;
    private Gson gson = new Gson();

    private final String ingredientFileName = "ingredient.json";
    private final String allergiesFileName = "allergies.json";
    private final String religionsFileName = "religion.json";

    public void setFile(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
        openFile();
    }

    // ファイルを読み出し
    public void openFile() {

        // try-with-resources
        try (FileInputStream fileInputStream = context.openFileInput(fileName);
             BufferedReader reader= new BufferedReader(
                     new InputStreamReader(fileInputStream, "UTF-8"))) {

            String lineBuffer;
            while( (lineBuffer = reader.readLine()) != null ) {
                jsonData = lineBuffer;
            }

            if (fileName.equals(ingredientFileName)) {
                ingredientList = gson.fromJson(jsonData, new TypeToken<List<IngredientData>>(){}.getType());
            } else {
                list = gson.fromJson(jsonData, new TypeToken<List<Data>>(){}.getType());
            }

            Log.d("weiwei", "62:jsonData =" + jsonData);
            Log.d("weiwei", "ファイル読み込みに成功したZE");

        } catch (FileNotFoundException e) {
            Log.d("weiwei", "ファイルが無かったから初期化するZE");

            if (fileName.equals(ingredientFileName)) {
                initData();
            } else if (fileName.equals(religionsFileName)) {
                initData("religion");
            } else {
                initData("allergies");
            }
            switch(fileName) {
                case religionsFileName:
                    initData("religion");
                    break;
                case ingredientFileName:
                    initData();
                    break;
                case allergiesFileName:
                    initData("allergies");
                    break;
            }

        } catch (IOException e) {
            Log.d("weiwei", "エラーだZE:" + e);
            e.printStackTrace();
        }
    }

    public void saveData() {
        // try-with-resources
        try (FileOutputStream fileOutputstream = context.openFileOutput(fileName,
                Context.MODE_PRIVATE)){

            if (fileName.equals(ingredientFileName)) {
                jsonData = gson.toJson(ingredientList);
            } else {
                jsonData = gson.toJson(list);
            }
            Log.d("weiwei", "jsonData = " + jsonData);
            fileOutputstream.write(jsonData.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        //食材情報をセット
        Log.d("weiwei", "初期化の関数だZE");

        int strID;
        int ingredientNum = 0;
        IngredientData.Category[] categories = {vegetable, fruit, meat, seafood, seasoning, grain, dairy_product};
        String[] categoriesName = {"vegetables","fruits", "meats", "seafoods", "seasonings", "grains", "dairy_prosucts"};
        final int categoryLength = categories.length;

        for (int i = 0; i < categoryLength; i++) {
            ingredientNum = 0;
            while (true) {
                strID = context.getResources().getIdentifier(categoriesName[i] + "_" + ingredientNum, "string", context.getPackageName());
                if (strID == NULL || strID == 0) {
                    break;
                }
                Log.d("weiwei", "new Data(" + context.getResources().getString(strID) + ", " + categories[i] + ")");
                ingredientList.add(new IngredientData(context.getResources().getString(strID), categories[i]));
                ingredientNum++;
            }
        }

        for (IngredientData l : ingredientList) {
            Log.d("weiwei", "l = " + l.category + ", " + l.name + ", " + l.isSelect);
        }
        jsonData = gson.toJson(ingredientList);


    }
    
    private void initData(String firstStr) {

        int i = 0;
        while (true) {
            int strID = context.getResources().getIdentifier( firstStr + "_" + i, "string", context.getPackageName());
            if (strID == NULL || strID == 0) {
                break;
            }
            Log.d("weiwei", "strID = " + firstStr + "_" + i);
            Log.d("weiwei", "str = " + context.getResources().getString(strID));
            list.add(new Data(context.getResources().getString(strID)));
            i++;
        }
        jsonData = gson.toJson(list);
    }

    public void changeSelect(int position) {
        Data data = list.get(position);
        data.isSelect = !data.isSelect;
        Log.d("weiwei", "63:" + data.name + ":" + data.isSelect);
        list.set(position, new Data(data.name, data.isSelect));
    }

    public ArrayList<Data> getList() {
        return list;
    }

    public ArrayList<IngredientData> getIngredientList() {
        return ingredientList;
    }
}
