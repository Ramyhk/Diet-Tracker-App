package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    private UserProfile testUser;

    @BeforeEach
    void runBeforeEach() {
        testUser = new UserProfile("RamyTheGod", "1234A", 1);
    }

    @Test
    void testConstructor() {
        ArrayList<NutritionLog> logs = testUser.getLogs();
        assertEquals("RamyTheGod", testUser.getUserName());
        assertEquals("1234A", testUser.getPassWord());
        assertEquals(1, testUser.getMemberNum());
        assertTrue(logs.isEmpty());
    }

    @Test
    void testGetLogsEmpty() {
        ArrayList<NutritionLog> logs = testUser.getLogs();
        assertEquals(0, logs.size());
        assertTrue(logs.isEmpty());

    }

    @Test
    void testGetOneLog() {

        assertEquals(0, testUser.getLogs().size());
        NutritionLog nlog = new NutritionLog(LocalDate.of(2022, 10, 10), "Food",
                100, 150, 200, 5);
        testUser.addLog(nlog);

        assertFalse(testUser.getLogs().isEmpty());
        assertTrue(testUser.getLogs().contains(nlog));


    }

    @Test
    void testAddLog() {
        ArrayList<NutritionLog> logs = testUser.getLogs();
        assertEquals(0, logs.size());

        NutritionLog nlog = new NutritionLog(LocalDate.of(2022, 10, 10), "Food",
                100, 150, 200, 5);
        testUser.addLog(nlog);
        assertEquals(1, logs.size());
    }

    @Test
    void testAddTwoLog() {
        ArrayList<NutritionLog> logs = testUser.getLogs();
        assertEquals(0, logs.size());

        NutritionLog nlog = new NutritionLog(LocalDate.of(2022, 11, 10), "Goat Meat",
                10, 250, 210, 5);
        testUser.addLog(nlog);
        assertEquals(1, logs.size());

        NutritionLog nlog2 = new NutritionLog(LocalDate.of(2022, 11, 11), "Horsey Meat",
                1000, 20, 21, 0);
        testUser.addLog(nlog2);
        assertEquals(2, logs.size());
    }

    @Test
    void testAddTwiceAndOnceLog() {
        ArrayList<NutritionLog> logs = testUser.getLogs();
        assertEquals(0, logs.size());

        NutritionLog nlog = new NutritionLog(LocalDate.of(2022, 11, 10), "Goat Meat",
                10, 250, 210, 5);
        testUser.addLog(nlog);
        assertEquals(1, logs.size());

        NutritionLog nlog2 = new NutritionLog(LocalDate.of(2022, 11, 11), "Horsey Meat",
                1000, 20, 21, 0);
        testUser.addLog(nlog2);
        assertEquals(2, logs.size());

        testUser.addLog(nlog);
        assertEquals(3, logs.size());
    }


    @Test
    void testAddLogRemoveLog() {
        ArrayList<NutritionLog> logs = testUser.getLogs();
        assertEquals(0, logs.size());

        NutritionLog nlog = new NutritionLog(LocalDate.of(2022, 10, 10), "Food",
                100, 150, 200, 5);
        testUser.addLog(nlog);
        assertEquals(1, logs.size());

        testUser.removeLog(nlog);
        assertEquals(0, logs.size());
    }

    @Test
    void testAddTwoLogRemoveOne() {
        ArrayList<NutritionLog> logs = testUser.getLogs();
        assertEquals(0, logs.size());

        NutritionLog nlog = new NutritionLog(LocalDate.of(2022, 11, 10), "Goat Meat",
                10, 250, 210, 5);
        testUser.addLog(nlog);
        assertEquals(1, logs.size());

        NutritionLog nlog2 = new NutritionLog(LocalDate.of(2022, 11, 11), "Horsey Meat",
                1000, 20, 21, 0);
        testUser.addLog(nlog2);
        assertEquals(2, logs.size());

        testUser.removeLog(nlog);
        assertEquals(1, logs.size());
        assertTrue(logs.contains(nlog2));
        assertFalse(logs.contains(nlog));
    }

    @Test
    void testAddTwoLogRemoveTwo() {
        ArrayList<NutritionLog> logs = testUser.getLogs();
        assertEquals(0, logs.size());

        NutritionLog nlog = new NutritionLog(LocalDate.of(2022, 11, 10), "Goat Meat",
                10, 250, 210, 5);
        testUser.addLog(nlog);
        assertEquals(1, logs.size());

        NutritionLog nlog2 = new NutritionLog(LocalDate.of(2022, 11, 11), "Horsey Meat",
                1000, 20, 21, 0);
        testUser.addLog(nlog2);
        assertEquals(2, logs.size());

        assertTrue(logs.contains(nlog));
        assertTrue(logs.contains(nlog2));

        testUser.removeLog(nlog2);
        assertFalse(logs.contains(nlog2));
        assertTrue(logs.contains(nlog));

        testUser.removeLog(nlog);
        assertFalse(logs.contains(nlog));
        assertFalse(logs.contains(nlog2));
    }

    @Test
    void testProteinCount() {
        NutritionLog nlog = new NutritionLog(LocalDate.of(2022, 11, 10), "Goat Meat",
                10, 250, 210, 5);

        testUser.addLog(nlog);
        assertEquals(1, testUser.getLogs().size());
        assertEquals(10, testUser.proteinCount(LocalDate.of(2022, 11, 10)));
    }

    @Test
    void testProteinCountWrongDate() {
        NutritionLog nlog = new NutritionLog(LocalDate.of(2022, 11, 10), "Chicken",
                10, 250, 210, 5);

        testUser.addLog(nlog);
        assertEquals(1, testUser.getLogs().size());
        assertEquals(0, testUser.proteinCount(LocalDate.of(2021, 11, 10)));
    }

    @Test
    void testCarbCount() {
        NutritionLog nlog = new NutritionLog(LocalDate.of(2021, 1, 1), "Human Meat",
                1000, 1, 1, 1);

        testUser.addLog(nlog);
        assertEquals(1, testUser.getLogs().size());
        assertEquals(1, testUser.carbCount(LocalDate.of(2021, 1, 1)));

    }


    @Test
    void testSugarCount() {
        NutritionLog nlog = new NutritionLog(LocalDate.of(2030, 11, 11), "Grey Matter",
                0, 0, 0, 1000);

        testUser.addLog(nlog);
        assertEquals(1, testUser.getLogs().size());
        assertEquals(1000, testUser.sugarCount(LocalDate.of(2030, 11, 11)));

    }

    @Test
    void testFatCount() {
        NutritionLog nlog = new NutritionLog(LocalDate.of(2010, 5, 5), "Fish Eggs",
                5, 5, 30, 10);

        testUser.addLog(nlog);
        assertEquals(1, testUser.getLogs().size());
        assertEquals(30, testUser.fatCount(LocalDate.of(2010, 5, 5)));

    }


}

