package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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

import static com.example.shirokuma.whatsdish.IngredientListFormat.Category.dairy_product;
import static com.example.shirokuma.whatsdish.IngredientListFormat.Category.fruit;
import static com.example.shirokuma.whatsdish.IngredientListFormat.Category.grain;
import static com.example.shirokuma.whatsdish.IngredientListFormat.Category.meat;
import static com.example.shirokuma.whatsdish.IngredientListFormat.Category.seafood;
import static com.example.shirokuma.whatsdish.IngredientListFormat.Category.seasoning;
import static com.example.shirokuma.whatsdish.IngredientListFormat.Category.vegetable;
import static java.sql.Types.NULL;

public class File extends AppCompatActivity {

    String fileName = null;
    private String jsonIngredientData = null;
    static List<IngredientListFormat> ingredientList = new ArrayList<>();
    Gson gson = new Gson();

    File(String fileName) {
        this.fileName = fileName;
        openFile();
    }

    // ファイルを読み出し
    public void openFile() {

        // try-with-resources
        try (FileInputStream fileInputStream = openFileInput(fileName);
             BufferedReader reader= new BufferedReader(
                     new InputStreamReader(fileInputStream, "UTF-8"))) {

            String lineBuffer;
            while( (lineBuffer = reader.readLine()) != null ) {
                jsonIngredientData = lineBuffer;
            }
            ingredientList = gson.fromJson(jsonIngredientData, new TypeToken<List<IngredientListFormat>>(){}.getType());
            Log.d("weiwei", "ファイル読み込みに成功したZE");
        } catch (FileNotFoundException e) {
            Log.d("weiwei", "ファイルが無かったから初期化するZE");
            initData();
        } catch (IOException e) {
            Log.d("weiwei", "エラーだZE:" + e);
            e.printStackTrace();
        }
    }

    public void saveData() {
        // try-with-resources
        try (FileOutputStream fileOutputstream = openFileOutput(fileName,
                Context.MODE_PRIVATE)){

            jsonIngredientData = gson.toJson(ingredientList);
            fileOutputstream.write(jsonIngredientData.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initData() {
        if (fileName.equals("ingredient.json")) {
            //食材情報をセット
            Gson gson = new Gson();

            int strID;
            int ingredientNum = 0;
            IngredientListFormat.Category[] categories = {vegetable, fruit, meat, seafood, seasoning, grain, dairy_product};
            String[] categoriesName = {"vegetables","fruits", "meats", "seafoods", "seasonings", "grains", "dairy_prosucts"};
            final int categoryLength = categories.length;

            for (int i = 0; i < categoryLength; i++) {
                ingredientNum = 0;
                while (true) {
                    strID = getResources().getIdentifier(categoriesName[i] + "_" + ingredientNum, "string", getPackageName());
                    if (strID == NULL || strID == 0) {
                        break;
                    }
                    ingredientList.add(new IngredientListFormat(getResources().getString(strID), categories[i]));
                    ingredientNum++;
                }
            }

            for (IngredientListFormat l : ingredientList) {
                Log.d("weiwei", "l = " + l.category + ", " + l.name + ", " + l.isSelect);
            }
            jsonIngredientData = gson.toJson(ingredientList);
        }
    }
}
