package persistence;

import model.NutritionLog;
import model.UserProfile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

// Based on CPSC210 WorkRoom Example from:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Description: Represents a reader that reads userprofile from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads userprofile from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserProfile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserProfile(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses userprofile from JSON object and returns it
    private UserProfile parseUserProfile(JSONObject jsonObject) {
        String name = jsonObject.getString("userName");
        String pass = jsonObject.getString("passWord");
        int memberNum = jsonObject.getInt("memberNum");
        UserProfile user = new UserProfile(name, pass, memberNum);
        addNutritionLogs(user, jsonObject);
        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses logs from JSON object and adds them to userprofile
    private void addNutritionLogs(UserProfile user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("logs");
        for (Object json : jsonArray) {
            JSONObject nextLog = (JSONObject) json;
            addLog(user, nextLog);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses log from JSON object and adds it to userprofile
    private void addLog(UserProfile user, JSONObject jsonObject) {
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        String foodName = jsonObject.getString("foodName");
        int protein = jsonObject.getInt("protein");
        int carbohydrate = jsonObject.getInt("carbohydrate");
        int fat = jsonObject.getInt("fat");
        int sugar = jsonObject.getInt("sugar");

        NutritionLog log = new NutritionLog(date, foodName, protein, carbohydrate, fat, sugar);
        user.addLog(log);
    }
}
