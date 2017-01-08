package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import sample.application.Editor;
import sample.application.Login;
import sample.application.Training;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestingTabController implements Initializable {

    @FXML TabPane tabs = new TabPane();
    @FXML private Editor editor;
    @FXML private EditorController editorController;
    @FXML private Login publisher;
    @FXML private LoginController publisherController;
    @FXML private Training training;
    @FXML private TrainingController trainingController;


    public TestingTabController(){
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }



}
