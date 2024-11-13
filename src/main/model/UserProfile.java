package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;


// Description: Represents User Profiles that have a username and profile-specific password (limit 5 characters)
public class UserProfile implements Writable {

    private final String name; //username chosen by particular user for profile
    private final String pass; //password associated for a profile's username
    private final int memberNum; //the user's profile number

    private ArrayList<NutritionLog> logs; // list of nutrition logs attributed to user


    // REQUIRES: userName has String length > 0
    //           passWord has String length >=5
    //           memberNumber has int > 0
    // EFFECTS: the name of the profile is set to userName; pass is a string
    //          of minimum 5 character length, tied to the profile; memberNum
    //          is unique positive non-zero integer assigned to user in order of
    //          registration.


    public UserProfile(String userName, String passWord, int memberNumber) {
        this.logs = new ArrayList<>();
        this.name = userName;
        this.pass = passWord;
        this.memberNum = memberNumber;
    }

    // EFFECTS: returns the username of the UserProfile
    public String getUserName() {
        return name;
    }

    // EFFECTS: returns the password of the UserProfile
    public String getPassWord() {
        return pass;
    }

    // EFFECTS: returns the profile number
    public int getMemberNum() {
        return memberNum;
    }


    // EFFECTS: returns logs with a chosen NutritionLog added
    public void addLog(NutritionLog nl) {
        logs.add(nl);
        EventLog.getInstance().logEvent(new Event("Added the "
                + nl.getFoodName()
                + " food item to the User's Nutrition Logs on "
                + nl.getDate().toString()));
    }

    // EFFECTS: returns logs with a chosen NutritionLog removed
    public void removeLog(NutritionLog nl) {
        logs.remove(nl);
        EventLog.getInstance().logEvent(new Event("Removed the "
                + nl.getFoodName()
                + " food item from the User's Nutrition Logs on "
                + nl.getDate().toString()));
    }


    // EFFECTS: removes all logs from User Profile
    public void removeAllLogs() {
        logs.clear();
        EventLog.getInstance().logEvent(new Event("Cleared existing Nutrition Logs from User history"));
    }

    // EFFECTS: returns the list of NutritionLogs
    public ArrayList<NutritionLog> getLogs() {
        return logs;
    }


    // Helper function for day-derived macro count

    // EFFECTS: returns the logs attributed to a chosen day only
    public ArrayList<NutritionLog> getDaysLogs(LocalDate d) {
        ArrayList<NutritionLog> daysLogs = new ArrayList<>();
        for (NutritionLog nl : this.logs) {
            if (nl.getDate().equals(d)) {
                daysLogs.add(nl);

            }
        }
        return daysLogs;
    }

    // EFFECTS: returns the total protein count of a chosen day's NutritionLogs
    public int proteinCount(LocalDate d) {
        ArrayList<NutritionLog> daysLogs = this.getDaysLogs(d);
        int daysProtein = 0;
        for (NutritionLog nl : daysLogs) {
            daysProtein += nl.getProtein();
        }
        return daysProtein;
    }

    // EFFECTS: returns the total carbohydrate count of a chosen day's NutritionLogs
    public int carbCount(LocalDate d) {
        ArrayList<NutritionLog> daysLogs = this.getDaysLogs(d);
        int daysCarbs = 0;
        for (NutritionLog nl : daysLogs) {
            daysCarbs += nl.getCarbohydrate();
        }
        return daysCarbs;
    }

    // EFFECTS: returns the total fat count of a chosen day's NutritionLogs
    public int fatCount(LocalDate d) {
        ArrayList<NutritionLog> daysLogs = this.getDaysLogs(d);
        int daysFats = 0;
        for (NutritionLog nl : daysLogs) {
            daysFats += nl.getFat();
        }
        return daysFats;
    }

    // EFFECTS: returns the total sugar count of a chosen day's NutritionLogs
    public int sugarCount(LocalDate d) {
        ArrayList<NutritionLog> daysLogs = this.getDaysLogs(d);
        int daysSugar = 0;
        for (NutritionLog nl : daysLogs) {
            daysSugar += nl.getSugar();
        }
        return daysSugar;
    }

    // Based on CPSC210 WorkRoom Example from:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("memberNum", memberNum);
        json.put("passWord", pass);
        json.put("userName", name);
        json.put("logs", logsToJson());
        return json;
    }

    // EFFECTS: returns logs from User Profile as Json Array
    private JSONArray logsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (NutritionLog log : logs) {
            jsonArray.put(log.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns string representing all Nutrition logs
    public String printLogs() {
        String output = new String();
        for (NutritionLog log : this.logs) {
            output += log.printSingleLog();
        }
        return output;
    }

//    public void saveProfileEvent(){
//
//    }
}
