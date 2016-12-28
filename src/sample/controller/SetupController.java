package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SetupController implements Initializable {

    @FXML Button devButton = new Button();
    @FXML Button pubButton = new Button();
    @FXML TabPane tabs = new TabPane();


    private int currentScene = 0;

    public SetupController(){
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        devButton.setOnAction(e->{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/sample/view/UI.fxml"));
                Scene scene = new Scene(root, 1200, 700);
                Stage currentStage = (Stage) devButton.getScene().getWindow();
                currentStage.setTitle("PrX");
                currentStage.setScene(scene);
                currentStage.show();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
    }

    private void updateScene(FXMLLoader loader, Button fromButton, int cScene){
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) fromButton.getScene().getWindow();
            SetupController controller =  loader.getController();
            controller.setScene(cScene);
            currentStage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setScene(int scene){
        currentScene = scene;
    }



}
