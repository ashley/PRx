package sample.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.kohsuke.github.GitHub;
import sample.controller.Controller;

import java.io.IOException;

/**
 * Created by ashleychen on 11/25/16.
 */
public class Tree {
    private GitHub gitHub;

    public void display(Button btn)throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/UI.fxml"));

        Controller controller = new Controller();
        controller.setupController(gitHub);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 700);
        Stage currentStage = (Stage) btn.getScene().getWindow();
        currentStage.setTitle("PrX");
        currentStage.setScene(scene);
        currentStage.show();
    }

    public void setupTree (GitHub _gitHub){
        gitHub = _gitHub;
    }
}
