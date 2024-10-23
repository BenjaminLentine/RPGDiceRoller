import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class Main extends Application {

    private static final String dataFolderPath = "data";
    private static final String savedRollsFilePath = dataFolderPath + "/savedRolls.txt";

    //Main Menu Buttons
    Stage window;
    Button rollD20;
    TextField d20Mod;
    Button rollD12;
    TextField d12Mod;
    Button rollD10;
    TextField d10Mod;
    Button rollD8;
    TextField d8Mod;
    Button rollD6;
    TextField d6Mod;
    Button rollD4;
    TextField d4Mod;
    Button rollD100;
    TextField d100Mod;

    CheckBox advantageCheckBox;
    CheckBox disadvantageCheckBox;

    Button clearAllFields;
    Button openAdvancedMenu;

    //Advancced Roll Menu
    Button backToMainMenu;
    Label diceRollSummary;
    Button rollAdvancedRoll;
    Button saveRollToStorage;
    Button loadRollFromStorage;
    Button advClearContent;
    TextField advD20Count;
    TextField advD20Modifier;
    CheckBox advD20Advantage;
    CheckBox advD20Disadvantage;
    TextField advD12Count;
    TextField advD12Modifier;
    TextField advD10Count;
    TextField advD10Modifier;
    TextField advD8Count;
    TextField advD8Modifier;
    TextField advD6Count;
    TextField advD6Modifier;
    TextField advD4Count;
    TextField advD4Modifier;
    TextField advD100Count;
    TextField advD100Modifier;
    TextField advAdditionalModifierField;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

//QuickRoll / Main Menu
        boolean advantage = false;
        boolean disadvantage = false;

        advantageCheckBox = new CheckBox("Advantage");
        disadvantageCheckBox = new CheckBox("Disadvantage");

        rollD20 = new Button("D 20");
        rollD12 = new Button("D 12");
        rollD10 = new Button("D 10");
        rollD8 = new Button("D 8");
        rollD6 = new Button("D 6");
        rollD4 = new Button("D 4");
        rollD100 = new Button ("D 100");

        d20Mod = new TextField();
        d20Mod.setPromptText("Modifier");
        configureTextFieldsOnlyNumbersAndSymbols(d20Mod);

        d12Mod = new TextField();
        d12Mod.setPromptText("Modifier");
        configureTextFieldsOnlyNumbersAndSymbols(d12Mod);

        d10Mod = new TextField();
        d10Mod.setPromptText("Modifier");
        configureTextFieldsOnlyNumbersAndSymbols(d10Mod);

        d8Mod = new TextField();
        d8Mod.setPromptText("Modifier");
        configureTextFieldsOnlyNumbersAndSymbols(d8Mod);

        d6Mod = new TextField();
        d6Mod.setPromptText("Modifier");
        configureTextFieldsOnlyNumbersAndSymbols(d6Mod);

        d4Mod = new TextField();
        d4Mod.setPromptText("Modifier");
        configureTextFieldsOnlyNumbersAndSymbols(d4Mod);

        d100Mod = new TextField();
        d100Mod.setPromptText("Modifier");
        configureTextFieldsOnlyNumbersAndSymbols(d100Mod);

        clearAllFields = new Button("Clear All");
        openAdvancedMenu = new Button("Advanced Rolls");

        VBox adOrDisadBox = new VBox(5);
        adOrDisadBox.getChildren().addAll(advantageCheckBox, disadvantageCheckBox);

        VBox d20Box = new VBox(5);
        d20Box.setAlignment(Pos.CENTER);
        d20Box.getChildren().addAll(rollD20, d20Mod);

        VBox d12Box = new VBox(5);
        d12Box.setAlignment(Pos.CENTER);
        d12Box.getChildren().addAll(rollD12, d12Mod);

        VBox d10Box = new VBox(5);
        d10Box.setAlignment(Pos.CENTER);
        d10Box.getChildren().addAll(rollD10, d10Mod);

        VBox d8Box = new VBox(5);
        d8Box.setAlignment(Pos.CENTER);
        d8Box.getChildren().addAll(rollD8, d8Mod);

        VBox d6Box = new VBox(5);
        d6Box.setAlignment(Pos.CENTER);
        d6Box.getChildren().addAll(rollD6, d6Mod);

        VBox d4Box = new VBox(5);
        d4Box.setAlignment(Pos.CENTER);
        d4Box.getChildren().addAll(rollD4, d4Mod);

        VBox d100Box = new VBox(5);
        d100Box.setAlignment(Pos.CENTER);
        d100Box.getChildren().addAll(rollD100, d100Mod);

        GridPane quickRoll = new GridPane();
        quickRoll.setVgap(20);
        quickRoll.setPadding(new Insets(10,10,10,10));
        GridPane.setColumnSpan(adOrDisadBox,2);
        quickRoll.add(adOrDisadBox, 0, 0);
        quickRoll.add(d20Box, 1, 1);
        quickRoll.add(d12Box, 0, 2);
        quickRoll.add(d10Box, 2, 2);
        quickRoll.add(d8Box,0,3);
        quickRoll.add(d6Box, 2, 3);
        quickRoll.add(d4Box, 0, 4);
        quickRoll.add(d100Box, 2,4);
        quickRoll.add(clearAllFields, 0, 5, 3,1);
        GridPane.setHalignment(clearAllFields, HPos.CENTER);
        quickRoll.add(openAdvancedMenu, 0, 6, 3, 1);
        GridPane.setHalignment(openAdvancedMenu, HPos.CENTER);



        BorderPane mainMenuLayout = new BorderPane();
        mainMenuLayout.setCenter(quickRoll);

        advantageCheckBox.setOnAction(e -> {
            if (disadvantageCheckBox.isSelected()) {
                disadvantageCheckBox.setSelected(false);
            }
        });

        disadvantageCheckBox.setOnAction(e ->{
            if (advantageCheckBox.isSelected()) {
                advantageCheckBox.setSelected(false);
            }
        });

        configureDiceRollButtons(rollD20, d20Mod, 20,advantageCheckBox,disadvantageCheckBox);
        configureDiceRollButtons(rollD12, d12Mod, 12,advantageCheckBox,disadvantageCheckBox);
        configureDiceRollButtons(rollD10, d10Mod, 10,advantageCheckBox,disadvantageCheckBox);
        configureDiceRollButtons(rollD8, d8Mod, 8,advantageCheckBox,disadvantageCheckBox);
        configureDiceRollButtons(rollD6, d6Mod, 6,advantageCheckBox,disadvantageCheckBox);
        configureDiceRollButtons(rollD4, d4Mod, 4,advantageCheckBox,disadvantageCheckBox);
        configureDiceRollButtons(rollD100, d100Mod, 100,advantageCheckBox,disadvantageCheckBox);


        clearAllFields.setOnAction(e ->{
            advantageCheckBox.setSelected(false);
            disadvantageCheckBox.setSelected(false);
            d20Mod.setText("");
            d12Mod.setText("");
            d10Mod.setText("");
            d8Mod.setText("");
            d6Mod.setText("");
            d4Mod.setText("");
            d100Mod.setText("");
        });

//Advanced Roll Screen


        rollAdvancedRoll = new Button("Roll");
        backToMainMenu = new Button("Back");
        saveRollToStorage = new Button("Save");
        loadRollFromStorage = new Button("Load");
        advClearContent = new Button("Clear");
        diceRollSummary = new Label("");
        Label advancedRollMenuLabel = new Label("Advanced Custom Rolls");
        Separator separator = new Separator();
        separator.setPrefWidth(300);

        advD20Count = new TextField();
        configureTextFieldsOnlyNumbers(advD20Count);
        advD20Count.setPrefWidth(50);
        advD20Count.setPromptText("No. of Dice");
        advD20Modifier = new TextField();
        configureTextFieldsOnlyNumbersAndSymbols(advD20Modifier);
        advD20Modifier.setPrefWidth(50);
        advD20Modifier.setPromptText("Modifier");
        advD20Advantage = new CheckBox();
        Label advAdvantageCheckBoxLabel = new Label("Advantage");
        advD20Disadvantage = new CheckBox();
        Label advDisadvantageCheckBoxLabel = new Label("Disadvantage");
        advAdditionalModifierField = new TextField();
        advAdditionalModifierField.setMaxWidth(100);
        advAdditionalModifierField.setPromptText("Modifier");
        configureTextFieldsOnlyNumbersAndSymbols(advAdditionalModifierField);
        advD20Advantage.setOnAction(e -> {
            if (advD20Disadvantage.isSelected()) {
                advD20Disadvantage.setSelected(false);
            }
        });

        advD20Disadvantage.setOnAction(e ->{
            if (advD20Advantage.isSelected()) {
                advD20Advantage.setSelected(false);
            }
        });

        advD12Count = new TextField();
        configureTextFieldsOnlyNumbers(advD12Count);
        advD12Count.setPrefWidth(50);
        advD12Count.setPromptText("No. of Dice");
        advD12Modifier = new TextField();
        configureTextFieldsOnlyNumbersAndSymbols(advD12Modifier);
        advD12Modifier.setPrefWidth(50);
        advD12Modifier.setPromptText("Modifier");

        advD10Count = new TextField();
        configureTextFieldsOnlyNumbers(advD10Count);
        advD10Count.setPrefWidth(50);
        advD10Count.setPromptText("No. of Dice");
        advD10Modifier = new TextField();
        configureTextFieldsOnlyNumbersAndSymbols(advD10Modifier);
        advD10Modifier.setPrefWidth(50);
        advD10Modifier.setPromptText("Modifier");

        advD8Count = new TextField();
        configureTextFieldsOnlyNumbers(advD8Count);
        advD8Count.setPrefWidth(50);
        advD8Count.setPromptText("No. of Dice");
        advD8Modifier = new TextField();
        configureTextFieldsOnlyNumbersAndSymbols(advD8Modifier);
        advD8Modifier.setPrefWidth(50);
        advD8Modifier.setPromptText("Modifier");

        advD6Count = new TextField();
        configureTextFieldsOnlyNumbers(advD6Count);
        advD6Count.setPrefWidth(50);
        advD6Count.setPromptText("No. of Dice");
        advD6Modifier = new TextField();
        configureTextFieldsOnlyNumbersAndSymbols(advD6Modifier);
        advD6Modifier.setPrefWidth(50);
        advD6Modifier.setPromptText("Modifier");

        advD4Count = new TextField();
        configureTextFieldsOnlyNumbers(advD4Count);
        advD4Count.setPrefWidth(50);
        advD4Count.setPromptText("No. of Dice");
        advD4Modifier = new TextField();
        configureTextFieldsOnlyNumbersAndSymbols(advD4Modifier);
        advD4Modifier.setPrefWidth(50);
        advD4Modifier.setPromptText("Modifier");

        advD100Count = new TextField();
        configureTextFieldsOnlyNumbers(advD100Count);
        advD100Count.setPrefWidth(50);
        advD100Count.setPromptText("No. of Dice");
        advD100Modifier = new TextField();
        configureTextFieldsOnlyNumbersAndSymbols(advD100Modifier);
        advD100Modifier.setPrefWidth(50);
        advD100Modifier.setPromptText("Modifier");

        TextField[] advFields = {advD20Count, advD20Modifier, advD12Count, advD12Modifier, advD10Count, advD10Modifier, advD8Count, advD8Modifier, advD6Count, advD6Modifier, advD4Count, advD4Modifier, advD100Count, advD100Modifier};
        TextField[] advFieldsWithAddModifier = {advD20Count, advD20Modifier, advD12Count, advD12Modifier, advD10Count, advD10Modifier, advD8Count, advD8Modifier, advD6Count, advD6Modifier, advD4Count, advD4Modifier, advD100Count, advD100Modifier,advAdditionalModifierField};

        for (int i = 0; i < advFields.length; i+= 2) {
            TextField diceCountField = advFields[i];
            TextField modifierField = advFields[i + 1];

            modifierField.setDisable(true);

            diceCountField.textProperty().addListener((obvservable, oldvalue, newvalue) ->{
                if (newvalue.trim().isEmpty()) {
                    modifierField.setDisable(true);
                    modifierField.clear();
                } else {
                    modifierField.setDisable(false);
                }
            });
        }

        for (int i = 0; i < advFieldsWithAddModifier.length; i++) {
            TextField textField = advFieldsWithAddModifier[i];
            textField.textProperty().addListener((observable, oldvalue, newvalue) -> updateDiceRollSummary(advFields,advAdditionalModifierField,diceRollSummary));
        }

        Label advCountLabel = new Label("Amount");
        advCountLabel.setStyle("-fx-font-weight: bold;");
        Label advModifierLabel = new Label("Modifier");
        advModifierLabel.setStyle("-fx-font-weight: bold;");


        //advantage and disadvantage
        VBox advAdvantageBox = new VBox(5);
        advAdvantageBox.getChildren().addAll(advD20Advantage, advAdvantageCheckBoxLabel);
        advAdvantageBox.setAlignment(Pos.CENTER);

        VBox advDisadvantageBox = new VBox(5);
        advDisadvantageBox.getChildren().addAll(advD20Disadvantage, advDisadvantageCheckBoxLabel);
        advDisadvantageBox.setAlignment(Pos.CENTER);

    //setting the constraints to better format the GridPane
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(50);
        col1.setHalignment(HPos.CENTER);
        ColumnConstraints col2 = new ColumnConstraints();
//        col2.setHgrow(Priority.ALWAYS);
        col2.setHalignment(HPos.CENTER);
        col2.setPrefWidth(100);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPrefWidth(30);
        col3.setHalignment(HPos.CENTER);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHalignment(HPos.CENTER);
        col4.setPrefWidth(100);

        BorderPane advancedRollLayout = new BorderPane();
        GridPane advancedRollDiceLayout = new GridPane();
        advancedRollDiceLayout.setVgap(8);
        GridPane.setHalignment(advancedRollDiceLayout,HPos.CENTER);
        advancedRollDiceLayout.setAlignment(Pos.TOP_CENTER);
        advancedRollDiceLayout.setPadding(new Insets(10));
        advancedRollDiceLayout.getColumnConstraints().addAll(col1, col2, col3,col4);

    //Adding Content to GridPane
        advancedRollDiceLayout.add(advancedRollMenuLabel,0,0);
        GridPane.setColumnSpan(advancedRollMenuLabel, 4);
        GridPane.setHalignment(advancedRollMenuLabel,HPos.CENTER);
        advancedRollDiceLayout.add(separator, 0, 1);
        GridPane.setColumnSpan(separator, 4);
        GridPane.setHalignment(separator, HPos.CENTER);
        advancedRollDiceLayout.add(advAdvantageBox,0,2);
        GridPane.setColumnSpan(advAdvantageBox, 2);
        GridPane.setHalignment(advAdvantageBox,HPos.CENTER);
        advancedRollDiceLayout.add(advDisadvantageBox, 2, 2);
        GridPane.setColumnSpan(advDisadvantageBox, 2);
        GridPane.setHalignment(advDisadvantageBox,HPos.CENTER);

        //d20 row
        advancedRollDiceLayout.add(advCountLabel, 1, 3);
        advancedRollDiceLayout.add(advModifierLabel, 3, 3);
        advancedRollDiceLayout.add(new Label("D20: "), 0, 4);
        advancedRollDiceLayout.add(advD20Count, 1, 4);
        advancedRollDiceLayout.add(new Label(" + "), 2, 4);
        advancedRollDiceLayout.add(advD20Modifier, 3, 4);

        //d12 row
        advancedRollDiceLayout.add(new Label("D12: "), 0, 5);
        advancedRollDiceLayout.add(advD12Count, 1, 5);
        advancedRollDiceLayout.add(new Label(" + "), 2, 5);
        advancedRollDiceLayout.add(advD12Modifier, 3, 5);

        //d10 row
        advancedRollDiceLayout.add(new Label("D10: "), 0, 6);
        advancedRollDiceLayout.add(advD10Count, 1, 6);
        advancedRollDiceLayout.add(new Label(" + "), 2, 6);
        advancedRollDiceLayout.add(advD10Modifier, 3, 6);

        //d8 row
        advancedRollDiceLayout.add(new Label("D8: "), 0, 7);
        advancedRollDiceLayout.add(advD8Count, 1, 7);
        advancedRollDiceLayout.add(new Label(" + "), 2, 7);
        advancedRollDiceLayout.add(advD8Modifier, 3, 7);

        //d6 row
        advancedRollDiceLayout.add(new Label("D6: "), 0, 8);
        advancedRollDiceLayout.add(advD6Count, 1, 8);
        advancedRollDiceLayout.add(new Label(" + "), 2, 8);
        advancedRollDiceLayout.add(advD6Modifier, 3, 8);

        //d4 row
        advancedRollDiceLayout.add(new Label("D4: "), 0, 9);
        advancedRollDiceLayout.add(advD4Count, 1, 9);
        advancedRollDiceLayout.add(new Label(" + "), 2, 9);
        advancedRollDiceLayout.add(advD4Modifier, 3, 9);

        //d100 row
        advancedRollDiceLayout.add(new Label("D100: "), 0, 10);
        advancedRollDiceLayout.add(advD100Count, 1, 10);
        advancedRollDiceLayout.add(new Label(" + "), 2, 10);
        advancedRollDiceLayout.add(advD100Modifier, 3, 10);

        //Additional Modifiers
        Label advAdditionalModifierLabel = new Label("Additional Modifier");
        VBox advAdditionalModifierVBox = new VBox(5);
        advAdditionalModifierVBox.getChildren().addAll(advAdditionalModifierLabel, advAdditionalModifierField);
        advAdditionalModifierVBox.setAlignment(Pos.CENTER);
        advancedRollDiceLayout.add(advAdditionalModifierVBox, 0, 11);
        GridPane.setColumnSpan(advAdditionalModifierVBox,4);
        GridPane.setHalignment(advAdditionalModifierVBox,HPos.CENTER);

        //Summary Section
        advancedRollDiceLayout.add(diceRollSummary,0,12);
        GridPane.setColumnSpan(diceRollSummary, 4);

        //Button Row
        HBox ButtonRowHBox = new HBox(10);
        ButtonRowHBox.getChildren().addAll(rollAdvancedRoll, advClearContent, saveRollToStorage, loadRollFromStorage, backToMainMenu);
        ButtonRowHBox.setAlignment(Pos.CENTER);
        ButtonRowHBox.setPadding(new Insets(0,0,10,0));

        //Overal Advanced Roll Menu (BorderPane)
        advancedRollLayout.setCenter(advancedRollDiceLayout);
        advancedRollLayout.setBottom(ButtonRowHBox);

        //Advanced Roll Buttons
        advClearContent.setOnAction(e ->{
            clearAdvFields(advFields,advAdditionalModifierField,advD20Advantage,advD20Disadvantage);
        });



        Scene mainMenuScene = new Scene(mainMenuLayout, 300,500);
        Scene advancedRollMenuScene = new Scene(advancedRollLayout, 300, 500);
        window.setScene(mainMenuScene);
        window.show();

        //Change Scene Button Actions
        backToMainMenu.setOnAction(e -> window.setScene(mainMenuScene));
        openAdvancedMenu.setOnAction(e -> window.setScene(advancedRollMenuScene));
        rollAdvancedRoll.setOnAction(e ->{
            String advAdvantageCheck = "normal";
            if (advD20Advantage.isSelected()) {
                advAdvantageCheck = "advantage";
            } else if (advD20Disadvantage.isSelected()) {
                advAdvantageCheck = "disadvantage";
            }
            advancedRollDice(window,advancedRollMenuScene,advAdvantageCheck,advFields, advAdditionalModifierField);
        });
        saveRollToStorage.setOnAction(e -> {
            String advAdvantageCheck = "normal";
            if (advD20Advantage.isSelected()) {
                advAdvantageCheck = "advantage";
            } else if (advD20Disadvantage.isSelected()) {
                advAdvantageCheck = "disadvantage";
            }
            saveRoll(advAdvantageCheck,advFields,advAdditionalModifierField,advancedRollMenuScene);
        });
        loadRollFromStorage.setOnAction(e -> loadSavedRolls(window,advancedRollMenuScene,advFields,advAdditionalModifierField,advD20Advantage,advD20Disadvantage));

    }

    public void configureDiceRollButtons(Button rollButton, TextField modifierBox, int diceSides, CheckBox advantageBox, CheckBox disadvantageBox) {
        rollButton.setOnAction(e ->{
            String advantage = null;
            int modifier = 0;
            if (advantageCheckBox.isSelected() && diceSides == 20) {
                advantage = "advantage";
            } else if (disadvantageCheckBox.isSelected() && diceSides == 20) {
                advantage = "disadvantage";
            } else {
                advantage = "normal";
            }

            if (!modifierBox.getText().isEmpty() && !modifierBox.getText().matches("[+-]*")) {
                modifier = Integer.parseInt(modifierBox.getText());
            }

            diceRoller.quickRollAndShowResult(advantage,diceSides,modifier);
        });

    }

    public void configureTextFieldsOnlyNumbersAndSymbols(TextField textField) {
        textField.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if (newvalue.equals("+") && oldvalue.equals("-")) {
                textField.setText("+");
            } else if (newvalue.equals("-") && oldvalue.equals("+")) {
                textField.setText("-");
            } else if (newvalue.equals("+-") || newvalue.equals("-+") || newvalue.equals("--") || newvalue.equals("++")) {
                textField.setText(newvalue.substring(1));
            } else if (oldvalue.matches("[+-]*\\d+") && (newvalue.endsWith("+") || newvalue.endsWith("-"))){
                textField.setText(oldvalue);
            } else if (!newvalue.matches("[+-]?\\d*")) {
                textField.setText(newvalue.replaceAll("[^\\d-+]", ""));
            }
        });
    }

    public void configureTextFieldsOnlyNumbers(TextField textField) {
        textField.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if (!newvalue.matches("\\d*")) {
                textField.setText(newvalue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void advancedRollDice(Stage window, Scene advancedRollMenuScene, String advantage, TextField[] fields, TextField additionalModifier) {
        int[][] diceData = new int[8][3];
        diceData[0][0] = 20;
        diceData[1][0] = 12;
        diceData[2][0] = 10;
        diceData[3][0] = 8;
        diceData[4][0] = 6;
        diceData[5][0] = 4;
        diceData[6][0] = 100;

        for (int i = 0; i < fields.length; i += 2) {
            int count = fields[i].getText().isEmpty() ? 0 : Integer.parseInt(fields[i].getText());
            int modifier = fields[i + 1].getText().isEmpty() ? 0 : Integer.parseInt(fields[i + 1].getText());

            diceData[i/2][1] = count;
            diceData[i/2][2] = modifier;
        }
        int intAdditionalModifier = additionalModifier.getText().isEmpty() ? 0 : Integer.parseInt(additionalModifier.getText());

        int[][] diceRolls = compileInformation.advancedCreateArrayOfDiceRolled(advantage,diceData);
        int[] modiferArray = compileInformation.advancedCreateArrayOfModifiers(diceData);
        String summaryText = "";
        int finalTotal = 0;


        BorderPane advancedDiceSummaryLayout = new BorderPane();
        Scene advancedDiceSummaryScene = new Scene(advancedDiceSummaryLayout,300,500);
        Button OKButton = new Button("Okay");
        OKButton.setPadding(new Insets(10));

    //Displaying the label.
        for (int i = 0; i < 7; i++) {
            if (diceRolls[i].length == 0) {
                continue;
            }

            summaryText += diceData[i][1] + "D" + diceData[i][0];
            if (diceData[i][2] == 0)
                if (i == 0 && advantage == "advantage") {
                    summaryText += " (Advantage): ";
                } else if (i == 0 && advantage == "disadvantage"){
                    summaryText += " (Disadvantage): ";
                } else summaryText += ": ";
            else if(diceData[i][2] > 0)
                    if (i == 0 && advantage == "advantage") {
                        summaryText += " + " + diceData[i][2] + " (Advantage): ";
                    } else if (i == 0 && advantage == "disadvantage"){
                        summaryText += " + " + diceData[i][2] + " (Disadvantage): ";
                    }else summaryText += " + " + diceData[i][2] + ": ";
            else
                    if (i == 0 && advantage == "advantage") {
                        summaryText += " - " + (diceData[i][2] * -1) + " (Advantage): ";
                    } else if (i == 0 && advantage == "disadvantage"){
                        summaryText += " - " + (diceData[i][2] * -1) + " (Disadvantage): ";
                    }else summaryText += " - " + (diceData[i][2] * -1) + ": ";
            for (int j = 0; j < diceRolls[i].length; j++) {
                summaryText += diceRolls[i][j];
                if (j < diceRolls[i].length - 1)
                    summaryText += ", ";
            }
            summaryText += "\n";
            int diceRollTotal = 0;
            for (int j = 0; j < diceRolls[i].length; j++) {
                diceRollTotal += diceRolls[i][j];
            }
            if (diceData[i][1] != 1)
                summaryText += "Dice Total: " + diceRollTotal + "\n";
            summaryText += "Modifier: " + diceData[i][2] + "\n";
            summaryText += "Final Totoal: " + (diceRollTotal + diceData[i][2]);
            finalTotal += (diceRollTotal + diceData[i][2]);
            summaryText += "\n" + "\n";
        }
        if (!additionalModifier.getText().isEmpty()) {
            summaryText += "Roll Total: " + finalTotal;
            summaryText += "\nAdditional Modifier: " + additionalModifier.getText();
            finalTotal += Integer.parseInt(additionalModifier.getText());
        }
        summaryText += "\nFINAL TOTAL: " + finalTotal;

        Label summaryTextLabel = new Label(summaryText);
        summaryTextLabel.setPadding(new Insets(10));
        BorderPane.setAlignment(OKButton,Pos.CENTER);
        BorderPane.setAlignment(summaryTextLabel, Pos.CENTER_LEFT);



        advancedDiceSummaryLayout.setBottom(OKButton);
        advancedDiceSummaryLayout.setCenter(summaryTextLabel);
        window.setScene(advancedDiceSummaryScene);

        OKButton.setOnAction(e -> window.setScene(advancedRollMenuScene)); //This needs to go back to the roll screen somehow.

    }

    public void saveRoll (String advantage, TextField[] fields, TextField additionalModifier, Scene advancedRollMenuScene) {
        String entryTitle = compileInformation.savedEntryTitle(window,advancedRollMenuScene);
        if (entryTitle == null) {
            return;
        }
        int[][] diceData = new int[8][3];
        diceData[0][0] = 20;
        diceData[1][0] = 12;
        diceData[2][0] = 10;
        diceData[3][0] = 8;
        diceData[4][0] = 6;
        diceData[5][0] = 4;
        diceData[6][0] = 100;

        for (int i = 0; i < fields.length; i += 2) {
            int count = fields[i].getText().isEmpty() ? 0 : Integer.parseInt(fields[i].getText());
            int modifier = fields[i + 1].getText().isEmpty() ? 0 : Integer.parseInt(fields[i + 1].getText());

            diceData[i/2][1] = count;
            diceData[i/2][2] = modifier;
        }
        int intAdditionalModifier = additionalModifier.getText().isEmpty() ? 0 : Integer.parseInt(additionalModifier.getText());

        File dataFoler = new File(dataFolderPath);
        if (!dataFoler.exists()) {
            dataFoler.mkdir();
        }
        File savedRollsFile = new File(savedRollsFilePath);
        if (savedRollsFile.exists()) {
            try {
                savedRollsFile.createNewFile();
            } catch (IOException e) {
                System.out.println("There was an error creating the file." + e.getMessage());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savedRollsFilePath, true))) {
            writer.write("--ENTRY--");
            writer.newLine();
            writer.write("Title:" + entryTitle);
            writer.newLine();
            writer.write("Summary:" + compileInformation.getRollSummary(diceData, advantage, intAdditionalModifier));
            writer.newLine();
            for (int i = 0; i < diceData.length -1; i++) {
                writer.write(diceData[i][0] + "," + diceData[i][1] + "," + diceData[i][2]);
                writer.newLine();
            }
            writer.write("Add.:" + intAdditionalModifier);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("There was an error saving roll. " + e.getMessage());
        }

    }

    public void loadSavedRolls(Stage window, Scene advancedRollMenuScene, TextField[] advFields, TextField advAdditionalModifierField,CheckBox advantage, CheckBox disadvantage) {

        Button loadButton = new Button("Load");
        Button backButton = new Button("Back");
        Button deleteButton = new Button("Delete");
        HBox buttonsLayout = new HBox(10);
        buttonsLayout.setPadding(new Insets(10));
        buttonsLayout.getChildren().addAll(loadButton, deleteButton, backButton);
        buttonsLayout.setAlignment(Pos.CENTER);
        BorderPane loadMenu = new BorderPane();
        ListView<String> savedRollsListLayout = new ListView<>();
        savedRollsListLayout.setPadding(new Insets(10));

        loadMenu.setCenter(savedRollsListLayout);
        loadMenu.setBottom(buttonsLayout);
        loadButton.setAlignment(Pos.CENTER);

        Scene saveRollsListScene = new Scene(loadMenu);
        window.setScene(saveRollsListScene);

        backButton.setOnAction(e -> window.setScene(advancedRollMenuScene));


        List<SavedRollEntry> savedRolls = compileInformation.createEntryList(savedRollsFilePath);

        for (SavedRollEntry savedRoll : savedRolls) {
            savedRollsListLayout.getItems().add(savedRoll.toString());
        }

        loadButton.setOnAction(e -> {
            if (savedRollsListLayout.getSelectionModel().getSelectedItem() != null){
                int index = Integer.parseInt(savedRollsListLayout.getSelectionModel().getSelectedItem().split(":")[0]);
                clearAdvFields(advFields,advAdditionalModifierField,advantage,disadvantage);
                for (SavedRollEntry savedRoll : savedRolls) {
                    if (savedRoll.startIndex == index) {
                        if (savedRoll.summary.startsWith("Advantage")) {
                            advantage.setSelected(true);
                        } else if (savedRoll.summary.startsWith("Disadvantage")) {
                            disadvantage.setSelected(true);
                        }
                        for (int i = 0; i < 7; i++) {
        //                        System.out.println(savedRoll.diceData[i][0] + "," + savedRoll.diceData[i][1] + "," + savedRoll.diceData[i][2]);
                            if (savedRoll.diceData[i][1] != 0) {
                                advFields[i * 2].setText(String.valueOf(savedRoll.diceData[i][1]));
                                advFields[(i * 2) + 1].setText(String.valueOf(savedRoll.diceData[i][2]));
                            }
                        }
                        if (savedRoll.additionalModifier != 0) {
                            advAdditionalModifierField.setText(String.valueOf(savedRoll.additionalModifier));
                        }
                        break;
                    }
                }
                window.setScene(advancedRollMenuScene);
            }
        });

        deleteButton.setOnAction(e -> {
            String selectedItem = savedRollsListLayout.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                int index = Integer.parseInt(selectedItem.split(":")[0]);
                savedRollsListLayout.getItems().remove(selectedItem);
                SavedRollEntry toRemove = null;
                for (SavedRollEntry savedRoll : savedRolls) {
                    if (savedRoll.startIndex == index) {
                        toRemove = savedRoll;
                        break;
                    }
                }
                if (toRemove != null) {
                    savedRolls.remove(toRemove);
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(savedRollsFilePath))) {
                    for (SavedRollEntry savedRoll : savedRolls) {
                        writer.write("--ENTRY--\n");
                        writer.write("Title:" + savedRoll.getTitle() + "\n");
                        writer.write("Summary:" + savedRoll.getSummary() + "\n");
                        for (int i = 0; i < 7; i++) {
                            writer.write(savedRoll.getDiceData()[i][0] + "," +
                                    savedRoll.getDiceData()[i][1] + "," +
                                    savedRoll.getDiceData()[i][2] + "\n");
                        }
                        writer.write("Add.:" + savedRoll.getAdditionalModifier() +"\n");
                    }
                } catch (IOException ex) {
                    System.out.println("Error deleting entry: " + ex.getMessage());
                    ;
                }
            } else {
                System.out.println("No item selected to delete.");
            }


        });

    }

    public static void clearAdvFields(TextField[] advFields, TextField advAdditionalModifierField,CheckBox advantage, CheckBox disadvantage) {
        for (int i = 0; i < advFields.length; i++) {
            advFields[i].clear();
        }
        advAdditionalModifierField.clear();
        advantage.setSelected(false);
        disadvantage.setSelected(false);
    }

    private void updateDiceRollSummary(TextField[] advFields, TextField additionalModifierField, Label diceRollSummary) {
        StringBuilder summaryBuilder = new StringBuilder();

        for (int i = 0; i < advFields.length; i += 2) {
            TextField diceCountField = advFields[i];
            TextField modifierField = advFields[i + 1];

            String countText = diceCountField.getText().trim();
            String modifierText = modifierField.getText().trim();

            if (!countText.isEmpty() && !countText.equals("0")) {
                int diceType = getDiceType(i / 2);
                summaryBuilder.append(countText)
                        .append("D")
                        .append(diceType);
                if (!modifierText.isEmpty() && !modifierText.equals("0")) {
                    summaryBuilder.append(modifierText.startsWith("-") ? modifierText : "+" + modifierText);
                }
                summaryBuilder.append(", ");
            }
        }
        if (!additionalModifierField.getText().isEmpty() && !additionalModifierField.getText().equals("0")) {
            summaryBuilder.append(additionalModifierField.getText().startsWith("-") ? additionalModifierField.getText() : "+" + additionalModifierField.getText());
            summaryBuilder.append(", ");
        }

        if (summaryBuilder.length() > 0) {
            summaryBuilder.setLength(summaryBuilder.length() - 2);
        }

        diceRollSummary.setText(summaryBuilder.toString());

    }

    private int getDiceType(int number) {
        switch (number) {
            case 0:
                return 20;
            case 1:
                return 12;
            case 2:
                return 10;
            case 3:
                return 8;
            case 4:
                return 6;
            case 5:
                return 4;
            case 6:
                return 100;
            default:
                return -1;
        }
    }

}