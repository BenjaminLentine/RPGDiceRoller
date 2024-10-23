import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class diceRoller {

    public static void quickRollAndShowResult(String advantageCheck, int diceSides, int modifier) {

        int roll = 0;
        int tempRoll1 = 0;
        int tempRoll2 = 0;
        if (advantageCheck.equals("advantage")) {
            tempRoll1 = ThreadLocalRandom.current().nextInt(1, diceSides + 1);
            tempRoll2 = ThreadLocalRandom.current().nextInt(1, diceSides + 1);
            if (tempRoll1 > tempRoll2) {
                roll = tempRoll1;
            } else {
                roll = tempRoll2;
            }
        } else if (advantageCheck.equals("disadvantage")) {
            tempRoll1 = ThreadLocalRandom.current().nextInt(1, diceSides + 1);
            tempRoll2 = ThreadLocalRandom.current().nextInt(1, diceSides + 1);
            if (tempRoll1 < tempRoll2) {
                roll = tempRoll1;
            } else {
                roll = tempRoll2;
            }
        } else {
            roll = ThreadLocalRandom.current().nextInt(1, diceSides + 1);
        }
        int finalTotal = roll + modifier;

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Result");
        dialog.setHeaderText("Result Summary");

        ButtonType rerollButton = new ButtonType("Reroll", ButtonType.OK.getButtonData());
        ButtonType closeButton = new ButtonType("close", ButtonType.CANCEL.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(rerollButton, closeButton);

        String contentText = null;

        if (advantageCheck.equals("advantage")) {
            contentText = "1D" + diceSides + "(Advantage): " + tempRoll2 + " / " + tempRoll1 + "\n" +
                    "Final roll: " + roll;
        } else if (advantageCheck.equals("disadvantage")) {
            contentText = "1D" + diceSides + "(Disadvantage): " + tempRoll2 + " / " + tempRoll1 + "\n" +
                    "Final roll: " + roll;
        } else {
            contentText = "1D" + diceSides + ": " + roll;
        }

        if (modifier != 0) {
            if (modifier > 0) {
                contentText += "\n" +
                        "Modifier: +" + modifier + "\n" +
                        "Final Total: " + finalTotal;
            } else {
                contentText += "\n" +
                        "Modifier: " + modifier + "\n" +
                        "Final Total: " + finalTotal;
            }
        }
        if (diceSides == 20) {
            if (roll == 1) {
                contentText += "\nCritical Failure!";
            } else if (roll == 20) {
                contentText += "\nCritical Success";
            }
        }

        dialog.setContentText(contentText);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == rerollButton) {
            quickRollAndShowResult(advantageCheck, diceSides, modifier);
        }
    }
}
