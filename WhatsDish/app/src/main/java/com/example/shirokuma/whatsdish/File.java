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
    private ArrayList<IngredientData> ingredientList = new ArrayList<>();
    private ArrayList<Data> list = new ArrayList<>();
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
            switch (fileName) {
                case ingredientFileName:
                    ingredientList = gson.fromJson(jsonData, new TypeToken<List<IngredientData>>(){}.getType());
                    break;
                default:
                    list = gson.fromJson(jsonData, new TypeToken<List<Data>>(){}.getType());
                    Log.d("weiwei", "62:jsonData =" + jsonData);
                    break;
            }

            Log.d("weiwei", "ファイル読み込みに成功したZE");
            for (Data i : list) {
                Log.d("weiwei", "list.name = " + i.name + "list.isSelect = " + i.isSelect);
            }
        } catch (FileNotFoundException e) {
            Log.d("weiwei", "ファイルが無かったから初期化するZE");

            switch(fileName) {
                case religionsFileName:
                    initReligionData();
                    break;
                case ingredientFileName:
                    initIngredientData();
                    break;
                case allergiesFileName:
                    initAllergyData();
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

    private void initIngredientData() {
        //食材情報をセット
        Gson gson = new Gson();

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
                ingredientList.add(new IngredientData(context.getResources().getString(strID), categories[i]));
                ingredientNum++;
            }
        }

        for (IngredientData l : ingredientList) {
            Log.d("weiwei", "l = " + l.category + ", " + l.name + ", " + l.isSelect);
        }
        jsonData = gson.toJson(ingredientList);
    }

    private void initReligionData() {
        //宗教情報をセット
        String[] religionsName = {"buddhism", "christ", "hinduism", "islam", "judaism", "shinto"};

        for (String name : religionsName) {
            list.add(new Data(name));
        }
        jsonData = gson.toJson(list);
    }
    
    private void initAllergyData() {
        String[] allergiesName = {"shrimp", "crab", "buckwheat",  "wheat", "egg", "milk", "peanuts", "squid", "salmon_roe", "orange", "cashewnuts", "kiwi",
                                  "cow", "walnut", "sesame", "fish", "soy", "chiken", "banana", "mushroom", "pig", "peach", "yam", "apple", "gelatin"};

        for (String name : allergiesName) {
            list.add(new Data(name));
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
