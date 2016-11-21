package sample.controller;

import analyze.Analyze;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kohsuke.github.GHEmail;
import org.kohsuke.github.GitHub;
import sample.application.Login;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {

    @FXML TextField username = new TextField();
    @FXML PasswordField password = new PasswordField();
    @FXML Button loginButton = new Button();
    @FXML Label check = new Label();

    private String user;
    private String pass;
    private GitHub github;
    private String loginValid = null;
    private boolean login = false;

    public LoginController() {
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        loginButton.setOnAction((event) -> {
            String [] str = {user, pass};
            try {
                login = github(str[0],str[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            GHEmail email = new GHEmail();
            check.setText(loginValid);
            if (login){
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("/sample/view/UI.fxml"));
                    Scene scene = new Scene(root, 600, 700);
                    Stage currentStage = (Stage) loginButton.getScene().getWindow();
                    currentStage.setTitle("PrX");
                    currentStage.setScene(scene);
                    currentStage.show();
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }

        });


        username.textProperty().addListener((observable, oldValue, newValue) -> {
            user = newValue;
        });

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            pass = newValue;
        });


    }

    private boolean github(String user, String pass) throws IOException {
        github = GitHub.connectUsingPassword(user,pass);
        //System.out.println(github.getRateLimit());
        try {
            github.getMyself();
            loginValid = "Login Success";
            return true;
        }
        catch (IllegalStateException e){ //Null login
            loginValid = "Please enter something";
            return false;
        }
        catch(IOException e){ //Incorrect authorization or rate limit
            loginValid = "Invalid username or password";
            return false;
        }

    }




}
