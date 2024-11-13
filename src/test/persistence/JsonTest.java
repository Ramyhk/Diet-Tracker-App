package persistence;

import model.NutritionLog;
import model.UserProfile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


// Based on CPSC210 WorkRoom Example from:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// helper class for associated Json tests
public class JsonTest {
    protected void checkLog(LocalDate dateToday, String foodName, int protein, int carbohydrate, int fat, int sugar,
                            NutritionLog log) {

        assertEquals(dateToday, log.getDate());
        assertEquals(foodName, log.getFoodName());
        assertEquals(protein, log.getProtein());
        assertEquals(carbohydrate, log.getCarbohydrate());
        assertEquals(fat, log.getFat());
        assertEquals(sugar, log.getSugar());

    }
}
