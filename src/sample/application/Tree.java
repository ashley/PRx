package sample.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by ashleychen on 11/25/16.
 */
public class Tree {

    public void display(Button btn)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/UI.fxml"));
        Scene scene = new Scene(root, 600, 700);
        Stage currentStage = (Stage) btn.getScene().getWindow();
        currentStage.setTitle("PrX");
        currentStage.setScene(scene);
        currentStage.show();
    }
}
