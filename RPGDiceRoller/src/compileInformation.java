import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

public class compileInformation {

    public static int[][] advancedCreateArrayOfDiceRolled (String advantageCheck, int[][] diceData) {

        //diceData: [][0 = diceSides, 1 = diceCount, 2 = modifier]
        //diceRols: [][rolls]

        int[][] diceRolls = new int[7][];

        for (int i = 0; i < diceData.length - 1; i++) {

            if (diceData[i][1] == 0) {
                diceRolls[i] = new int[0];
                continue;
            }
            diceRolls[i] = new int[diceData[i][1]];

            for (int j = 0; j < diceData[i][1]; j++) {
                if (diceData[i][0] == 20 && !advantageCheck.equals("normal")) {
                    if (advantageCheck.equals("advantage")) {
                        int tempRoll1 = ThreadLocalRandom.current().nextInt(1, 21);
                        int tempRoll2 = ThreadLocalRandom.current().nextInt(1, 21);
                        diceRolls[i][j] = Math.max(tempRoll1, tempRoll2);
                    } else {
                        int tempRoll1 = ThreadLocalRandom.current().nextInt(1, 21);
                        int tempRoll2 = ThreadLocalRandom.current().nextInt(1, 21);
                        diceRolls[i][j] = Math.min(tempRoll1, tempRoll2);
                    }
                } else {
                    diceRolls[i][j] = ThreadLocalRandom.current().nextInt(1, diceData[i][0] + 1);
                }
            }

        }

        return diceRolls;

    }

    public static int[] advancedCreateArrayOfModifiers(int[][] diceData){

        int[] modifierArray = new int[8];
        for (int i = 0; i < 8; i++) {
            if (diceData[i][1] == 0) {
                modifierArray[i] = 0;
            } else {
                modifierArray[i] = diceData[i][2];
            }
        }

        return modifierArray;
    }

    public static String getRollSummary(int[][] diceData, String advantage, int additionalModifier) {
        String rollSummaryText = "";
        for (int i = 0; i < diceData.length; i++) {
            if (diceData[i][1] == 0) {
                continue;
            }
            if (diceData[i][0] == 20 && advantage == "advantage")
                rollSummaryText += "Advantage: ";
            else if (diceData[i][0] == 20 && advantage == "disadvantage")
                rollSummaryText += "Disadvantage: ";
            rollSummaryText += diceData[i][1] + "D" + diceData[i][0];
            if (diceData[i][2] != 0) {
                if (diceData[i][2] > 0) {
                    rollSummaryText += "+" + diceData[i][2];
                } else {
                    rollSummaryText += diceData[i][2];
                }
            }
            rollSummaryText += ", ";

        }
        if (additionalModifier != 0)
            if (additionalModifier > 0) {
                rollSummaryText += "+" + additionalModifier;
            } else {
                rollSummaryText += additionalModifier;
            }

        return rollSummaryText;
    }

    public static String savedEntryTitle(Stage window, Scene advancedRollMenuScene) {

        Stage popup = new Stage();
        popup.setTitle("Save Entry Title");

        AtomicReference<Boolean> isSaved = new AtomicReference<>(false);
        AtomicReference<String> entryTitle = new AtomicReference<>("Untitled");
        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        Button saveEntry = new Button("Save");
        Button backButton = new Button("Back");
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(saveEntry, backButton);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleField, buttonBox);

        Scene popupScene = new Scene(layout,300,200);
        popup.setScene(popupScene);

        saveEntry.setOnAction(e ->{
            if (!titleField.getText().isEmpty()) {
                entryTitle.set(titleField.getText());
            }
            isSaved.set(true);
            popup.close();

        });

        backButton.setOnAction(e -> {
            popup.close();
            window.setScene(advancedRollMenuScene);
        });

        popup.showAndWait();
        return isSaved.get() ? entryTitle.get() : null;
    }

    public static List<SavedRollEntry> createEntryList(String fileName) {
        List<SavedRollEntry> savedRolls = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = 1;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("--ENTRY--")) {
                    String title = reader.readLine().substring(6).trim();
                    String summary = reader.readLine().substring(8).trim();

                    int[][] diceData = new int[7][3];
                    for (int i = 0; i < 7; i++) {
                        line = reader.readLine();
                        if (line.matches("\\d+,\\d+,-?\\d+")) {
                            String[] parts = line.split(",");
                            diceData[i][0] = Integer.parseInt(parts[0]);
                            diceData[i][1] = Integer.parseInt(parts[1]);
                            diceData[i][2] = Integer.parseInt(parts[2]);
                        }
                    }
                    int additionalModifier = Integer.parseInt(reader.readLine().substring(5).trim());

                    SavedRollEntry entry = new SavedRollEntry(title, summary, diceData, additionalModifier, index);
                    savedRolls.add(entry);
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return savedRolls;
    }

}
