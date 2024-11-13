package model;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NutritionLogTest {
    private NutritionLog testLog;

    @BeforeEach
    void runBeforeEach() {
        testLog = new NutritionLog((LocalDate.of(2022, 10, 10)), "Horse", 100,
                50, 25, 5);
    }

    // Sel-note: test instances for later testing

    @Test
    public void testInIt() {
        assertEquals((LocalDate.of(2022, 10, 10)), testLog.getDate());
        assertEquals("Horse", testLog.getFoodName());
        assertEquals(100, testLog.getProtein());
        assertEquals(50, testLog.getCarbohydrate());
        assertEquals(25, testLog.getFat());
        assertEquals(5, testLog.getSugar());

    }

    @Test
    public void testLocalDate() {

        NutritionLog altTestOne = new NutritionLog((LocalDate.of(2022, 10, 10)),
                "Grey Meat", 1, 1, 1, 10);
        assertEquals((LocalDate.of(2022, 10, 10)), altTestOne.getDate());
        assertEquals((LocalDate.of(2011, 11, 11)),
                altTestOne.setDate(LocalDate.of(2011, 11, 11)));
        assertEquals(2011, altTestOne.getDate().getYear());


    }

}





