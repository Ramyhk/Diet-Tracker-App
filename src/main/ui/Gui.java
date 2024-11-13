package ui;

import model.Event;
import model.EventLog;
import model.NutritionLog;
import model.UserProfile;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.Image;


// Description: Gui for Nutrition Analytics App: DieTrack
public class Gui extends JFrame implements ActionListener {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private static final String JSON_STORE = "./data/userprofile.json";
    private UserProfile profile;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;


    // MODIFIES: this
    // EFFECTS: initialize graphics and start application
    public Gui() {
        super("DieTrack: Nutrition Tracking and Fitness Analytics");
        initializeGraphics();

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: sets JFrame, sets layout, and initializes graphics
    private void initializeGraphics() {

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(this);
        setVisible(true);

        menu();
    }

    // MODIFIES: this
    // EFFECTS: Adds Load and New User buttons to JFrame
    public void menu() {

        JButton loadButton = new JButton("Load Profile Data");
        JButton newUserButton = new JButton("Create New Profile");
        loadButton.setBounds((Gui.WIDTH / 2) - 100,
                (Gui.HEIGHT / 2) - 55, 200, 50);
        loadButton.setActionCommand("Load Profile Data");
        loadButton.addActionListener(this);

        newUserButton.setBounds((Gui.WIDTH / 2) - 100,
                (Gui.HEIGHT / 2) + 5, 200, 50);
        newUserButton.setActionCommand("Create New Profile");
        newUserButton.addActionListener(this);
        add(loadButton);
        add(newUserButton);
    }


    // MODIFIES: this
    // EFFECTS: Opens Create New User window or
    //          Opens Load Profile Data window based on the button selected (action performed)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create New Profile")) {
            getContentPane().removeAll();
            repaint();
            createProfile(); //helper

        } else if (e.getActionCommand().equals("Load Profile Data")) {

            loadProfile(); //helper
            getContentPane().removeAll();
            repaint();
            display();
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads user data from selected file
    private void loadProfile() {
        try {
            this.profile = jsonReader.read();
            // EventLog.getInstance().logEvent(new Event("Profile has been loaded from " + JSON_STORE));
            // System.out.println("Profile has been loaded from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load profile from" + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Save user data to selected profile
    private void saveProfile() {

        try {
            jsonWriter.open();
            jsonWriter.write(profile);
            jsonWriter.close();
            // EventLog.getInstance().logEvent(new Event("Saved to " + JSON_STORE));
            //System.out.println("Saved to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE + " Please try again later");
        }
    }


    // MODIFIES: this
    // EFFECTS: creates User Profile based on user inputs Username and Password
    // NOTE: TA approved SuppressWarning due to context of usage
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void createProfile() {
        JPanel panel = new JPanel();

        panel.add(new JLabel("Username"));
        JTextField username = new JTextField("", 20);
        panel.add(username);

        panel.add(new JLabel("Password"));
        JTextField password = new JTextField("", 20);
        panel.add(password);

        // Referenced from: https://www.codejava.net/java-core/the-java-language/java-8-lambda-listener-example
        JButton submitProfile = new JButton("Submit New Profile");
        submitProfile.addActionListener(e -> {
            this.profile = new UserProfile(username.getText(), password.getText(), 1);
            getContentPane().removeAll();
            repaint();
            display(); //helper
            setVisible(true);
        });
        panel.add(submitProfile);

        JLabel accountMessage = new JLabel("Please Enter Username and Password to Create DieTrack Profile");
        panel.add(accountMessage);

        JLabel div = new JLabel();
        div.setPreferredSize(new Dimension(WIDTH, 75));
        panel.add(div);

        ImageIcon photo = new ImageIcon(new ImageIcon("data/arnie.jpg").getImage()
                .getScaledInstance(650,450, Image.SCALE_DEFAULT));
        JLabel photoLabel = new JLabel(photo);
        panel.add(photoLabel);

        add(panel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Displays Nutrition Log information for every log in Nutrition Logs in submission order
    // NOTE: TA approved SuppressWarning due to context of usage
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void display() {
        JPanel panel2 = new JPanel();
        panel2.setMinimumSize(new Dimension(WIDTH, HEIGHT / 2));
        ArrayList<NutritionLog> logs = this.profile.getLogs();
        for (NutritionLog log : logs) {
            panel2.add(new JLabel(log.printSingleLog(), SwingConstants.CENTER));
        }
        JLabel emptyImage = new JLabel();
        emptyImage.setPreferredSize(new Dimension(WIDTH, 10));
        panel2.add(emptyImage);

        panel2.add(new JLabel("Date of Consumption (YYYY-MM-DD)"));
        JTextField date = new JTextField("", 20);
        panel2.add(date);

        panel2.add(emptyImage);

        panel2.add(new JLabel("Food Item"));
        JTextField foodName = new JTextField("", 20);
        panel2.add(foodName);

        panel2.add(emptyImage);

        panel2.add(new JLabel("Protein Count (g)"));
        JTextField protein = new JTextField("", 20);
        panel2.add(protein);

        panel2.add(emptyImage);

        panel2.add(new JLabel("Carbohydrate Count (g)"));
        JTextField carbohydrate = new JTextField("", 20);
        panel2.add(carbohydrate);

        panel2.add(emptyImage);

        panel2.add(new JLabel("Fat Count (g)"));
        JTextField fat = new JTextField("", 20);
        panel2.add(fat);

        panel2.add(emptyImage);

        panel2.add(new JLabel("Sugar Count (g)"));
        JTextField sugar = new JTextField("", 20);
        panel2.add(sugar);

        panel2.add(emptyImage);

        JButton submitLog = new JButton("Submit New Log");
        submitLog.addActionListener(e -> {
            this.profile.addLog(new NutritionLog(LocalDate.parse(date.getText()),
                    foodName.getText(),
                    Integer.parseInt(protein.getText()),
                    Integer.parseInt(carbohydrate.getText()),
                    Integer.parseInt(fat.getText()),
                    Integer.parseInt(sugar.getText())));
            getContentPane().removeAll();
            repaint();
            display(); //helper
            setVisible(true);
        });

        panel2.add(submitLog);

        JButton removeAllLog = new JButton("Remove All Nutrition Logs");
        removeAllLog.addActionListener(r -> {
            this.profile.removeAllLogs();
            getContentPane().removeAll();
            repaint();
            display();
            setVisible(true);
        });

        panel2.add(removeAllLog);

        JButton saveButton = new JButton("Save Profile Data");
        saveButton.addActionListener(h -> saveProfile());

        panel2.add(saveButton);

        add(panel2);
        setVisible(true);
    }

    //Referenced from: https://www.clear.rice.edu/comp310/JavaResources/frame_close.html
    // MODIFIES: this
    // EFFECTS: Provides user with option to save before closing program
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            int close = JOptionPane.showConfirmDialog(null,
                    "Would you like to save DieTrack before quitting?",
                    "Closing DieTrack?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (close == JOptionPane.YES_OPTION) {
                saveProfile();
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString() + "\n");
                }
            } else {                                             // 'no' selected from options
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString() + "\n");
                }
            }
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: runs the main method of the Gui class=
    public static void main(String[] args) {
        new Gui();
    }
}