package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

// Description: Represents nutrition tracker/log, enabling  input of  food item macronutrients (in grams)

public class NutritionLog implements Writable {

    private LocalDate date; // date in which food was logged
    private final String foodName; // name of food chosen for the nutrition log input
    private final int protein;  // protein (macronutrient) content for item
    private final int carbohydrate; // carbohydrate (macronutrient) content for item
    private final int fat; // fat (macronutrient) content for item
    private final int sugar; // sugar content for item


    // REQUIRES: protein, carbohydrate, fat, sugar >= 0

    // EFFECTS: the date of when the NutritionLog was created is dateToday.
    //          the name attributed to the food of a NutritionLog is foodName.
    //          the protein content in the food of the NutritionLog is protein.
    //          the carbohydrates in the food of the NutritionLog is carbohydrate
    //          the fats in the food of the NutritionLog is fat
    //          the sugars in the food of the NutritionLog is sugar

    public NutritionLog(LocalDate dateToday, String foodName, int protein, int carbohydrate, int fat, int sugar) {
        this.date = dateToday;
        this.foodName = foodName;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.sugar = sugar;
    }

    // EFFECTS: returns the date of a NutritionLog
    public LocalDate getDate() {
        return this.date;
    }

    // MODIFIES: date of type LocalDate
    // EFFECTS: sets/changes the date of a NutritionLog
    public LocalDate setDate(LocalDate date) {
        this.date = date;
        return date;
    }

    // EFFECTS: returns the food name of a NutritionLog
    public String getFoodName() {
        return foodName;
    }

    // EFFECTS: returns the protein from a NutritionLog
    public int getProtein() {
        return protein;
    }

    // EFFECTS: returns the carbohydrate content from a NutritionLog
    public int getCarbohydrate() {
        return carbohydrate;
    }

    // EFFECTS: returns the fat from a NutritionLog
    public int getFat() {
        return fat;
    }

    // EFFECTS: returns the sugar from a NutritionLog
    public int getSugar() {
        return sugar;
    }


    // Based on CPSC210 WorkRoom Example from:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("foodName", foodName);
        json.put("protein", protein);
        json.put("carbohydrate", carbohydrate);
        json.put("fat", fat);
        json.put("sugar", sugar);
        return json;
    }

    // EFFECTS: returns string appending all information for single log
    public String printSingleLog() {
        String logString = new String();
        logString = "Date: " + date
                + " Food name: " + foodName + "g "
                + " Protein count: " + protein + "g "
                + " Carb count: " + carbohydrate + "g "
                + " Fat count: " + fat + "g "
                + " Sugar count: " + sugar + "g ";

        return logString;
    }
}

