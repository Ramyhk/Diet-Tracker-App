package persistence;

import model.NutritionLog;
import model.UserProfile;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Based on CPSC210 WorkRoom Example from:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// tests for methods in Json Reader class
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            UserProfile user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyProfile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyProfile.json");
        try {
            UserProfile user = reader.read();
            assertEquals("RamaDaGahd", user.getUserName());
            assertEquals("00000", user.getPassWord());
            assertEquals(0, user.getMemberNum());
            assertEquals(0, user.getLogs().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUserProfile() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUserProfile.json");
        try {
            UserProfile user = reader.read();
            assertEquals("Ramy", user.getUserName());
            assertEquals("12345", user.getPassWord());
            assertEquals(0, user.getMemberNum());

            List<NutritionLog> logs = user.getLogs();
            assertEquals(1, logs.size());
            checkLog(LocalDate.parse("2022-10-10"), "Cow Meat", 2, 3, 4, 5, logs.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
