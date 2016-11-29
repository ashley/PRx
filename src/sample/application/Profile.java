package sample.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kohsuke.github.GitHub;
import sample.controller.Controller;
import sample.controller.ProfileController;

import java.io.IOException;

/**
 * Created by ashleychen on 11/21/16.
 */

public class Profile {

    private GitHub gitHub;
    public void display(Button btn)throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Profile.fxml"));


        ProfileController profileController = new ProfileController();
        profileController.setupProfileController(gitHub);
        loader.setController(profileController);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 700);
        Stage currentStage = (Stage) btn.getScene().getWindow();
        currentStage.setTitle("PrX");
        currentStage.setScene(scene);
        currentStage.show();
    }

    public void setupProfile(GitHub _github){
        gitHub = _github;
    }
}
