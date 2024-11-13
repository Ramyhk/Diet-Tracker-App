package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.NutritionLog;
import model.UserProfile;
import sun.rmi.runtime.Log;
import sun.rmi.runtime.NewThreadAction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Based on CPSC210 WorkRoom Example from:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// tests for methods in Json Writer class
class JsonWriterTest extends JsonTest {

    private NutritionLog log;

    @BeforeEach
    void setup() {
        log = new NutritionLog(LocalDate.parse("2022-10-31"), "Swine Meat", 1, 2, 3, 4);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            UserProfile userProfile = new UserProfile("Ramathus", "12345", 0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyProfile() {
        try {
            UserProfile userProfile = new UserProfile("RamaDaGahd", "00000", 0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyProfile.json");
            writer.open();
            writer.write(userProfile);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyProfile.json");
            userProfile = reader.read();
            assertEquals("RamaDaGahd", userProfile.getUserName());
            assertEquals("00000", userProfile.getPassWord());
            assertEquals(0, userProfile.getMemberNum());
            assertEquals(0, userProfile.getLogs().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            UserProfile user = new UserProfile("Ramy", "12345", 0);
            user.addLog(log);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUserProfile.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUserProfile.json");
            user = reader.read();
            assertEquals("Ramy", user.getUserName());
            assertEquals("12345", user.getPassWord());
            assertEquals(0, user.getMemberNum());

            List<NutritionLog> logs = user.getLogs();
            assertEquals(1, logs.size());
            checkLog(LocalDate.parse("2022-10-31"), "Swine Meat", 1, 2, 3, 4,
                    logs.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}