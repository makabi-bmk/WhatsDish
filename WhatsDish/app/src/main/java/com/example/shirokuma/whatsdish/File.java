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

import static com.example.shirokuma.whatsdish.IngredientData.categories;
import static com.example.shirokuma.whatsdish.IngredientData.categoryNames;

public class File {

    private String fileName = null;
    private String jsonData = null;
    private ArrayList<Data> list = new ArrayList<>();
    private Context context = null;
    private Gson gson = new Gson();

    private final String ingredientFileName = "ingredient.json";
    private final String allergiesFileName = "allergies.json";
    private final String religionsFileName = "religion.json";

    //ingredientFile用の変数
    private ArrayList<IngredientData> ingredientList;
    static int[] firstElementNumbers;

    void setFile(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
        ingredientList = new ArrayList<>();
        openFile();
        if (fileName.equals(ingredientFileName)) {
            firstElementNumbers = new int[categories.length];
            setFirstElementNum();
        }
    }

    // ファイルを読み出し
    private void openFile() {

        try (FileInputStream fileInputStream = context.openFileInput(fileName);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(fileInputStream, "UTF-8"))) {
            String lineBuffer;
            while ((lineBuffer = reader.readLine()) != null) {
                jsonData = lineBuffer;
            }
            if (fileName.equals(ingredientFileName)) {
                Log.d("weiwei", "openFile:jsonData = " + jsonData);
                ingredientList = gson.fromJson(jsonData, new TypeToken<List<IngredientData>>() {
                }.getType());
            } else {
                list = gson.fromJson(jsonData, new TypeToken<List<Data>>() {
                }.getType());
            }
        } catch (FileNotFoundException e) {
            switch (fileName) {
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
            e.printStackTrace();
        }
    }

    void saveData() {
        try (FileOutputStream fileOutputstream = context.openFileOutput(fileName,
                Context.MODE_PRIVATE)) {
            if (fileName.equals(ingredientFileName)) {
                jsonData = gson.toJson(ingredientList);
                Log.d("weiwei", "saveData:jsonData = " + jsonData);
            } else {
                jsonData = gson.toJson(list);
            }
            fileOutputstream.write(jsonData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFirstElementNum(){
        int i = 0;
        int cnt = 0;
        firstElementNumbers[0] = 0;
        for (IngredientData data : ingredientList) {
            if (data.category != categories[i]) {
                if (++i >= categories.length) {
                    break;
                } else {
                    Log.d("weiwei", categories[i] +" = " + cnt);
                    firstElementNumbers[i] = cnt;
                }
            }
            cnt++;
        }
    }

    private void initData() {
        int strID;
        final int categoryLength = categoryNames.length;

        for (int i = 0; i < categoryLength; i++) {
            int j = 0;
            Log.d("weiwei", "category = " + categoryNames[i]);
            while (true) {
                strID = context.getResources().getIdentifier(categoryNames[i] + "_var_" + j, "string", context.getPackageName());
                if (strID == 0) {
                    break;
                }
                ingredientList.add(new IngredientData(context.getResources().getString(strID), categories[i]));
                j++;
            }
        }
        jsonData = gson.toJson(ingredientList);
        Log.d("weiwei", "initData:jsonData = " + jsonData);
    }

    private void initData(String firstStr) {
        int i = 0;
        while (true) {
            int strID = context.getResources().getIdentifier(firstStr + "_" + i, "string", context.getPackageName());
            if (strID == 0) {
                break;
            }
            list.add(new Data(context.getResources().getString(strID)));
            i++;
        }
        jsonData = gson.toJson(list);
    }

    void changeSelect(int position) {
        boolean isIngredientFile = fileName.equals(ingredientFileName);
        if (isIngredientFile) {
            IngredientData data = ingredientList.get(position);
            data.isSelect = !data.isSelect;
            ingredientList.set(position, new IngredientData(data.name, data.category, data.isSelect));
        } else {
            Data data = list.get(position);
            data.isSelect = !data.isSelect;
            list.set(position, new Data(data.name, data.isSelect));
        }
    }

    void changeSelectToTrue(int elementNum) {
        boolean isIngredientFile = fileName.equals(ingredientFileName);
        if (isIngredientFile) {
            IngredientData data = ingredientList.get(elementNum);
            if (!data.isSelect) {
                ingredientList.set(elementNum, new IngredientData(data.name, data.category, true));
            }
        } else {
            Data data = list.get(elementNum);
            if (!data.isSelect) {
                list.set(elementNum, new Data(data.name, true));
            }
        }
    }

    void changeSelectToFalse(int elementNum) {
        boolean isIngredientFile = fileName.equals(ingredientFileName);
        if (isIngredientFile) {
            IngredientData data = ingredientList.get(elementNum);
            if (data.isSelect) {
                ingredientList.set(elementNum, new IngredientData(data.name, data.category, false));
            }
        } else {
            Data data = list.get(elementNum);
            if (data.isSelect) {
                list.set(elementNum, new Data(data.name, false));
            }
        }
    }

    Data getData(int position) {
        return list.get(position);
    }

    IngredientData getIngredientData(int position) {
        return ingredientList.get(position);
    }

    public ArrayList<Data> getList() {
        return list;
    }

    public ArrayList<IngredientData> getIngredientList() {
        return ingredientList;
    }

}
