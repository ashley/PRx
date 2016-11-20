package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import analyze.*;

import java.io.File;
import java.util.ArrayList;

public class Controller {

    @FXML private Button myButton;
    @FXML private TextField beforeText;
    @FXML private TextField afterText;
    @FXML private TextArea outputTextArea;
    private String beforeLink;
    private String afterLink;

    public Controller(){
        //String[] str = {"/Users/ashleychen/Desktop/REUSE/REUSE/sealuzh-tools-changedistiller-d7ceec136e94/resources/testdata/src_change/TestLeft.java", "/Users/ashleychen/Desktop/REUSE/REUSE/sealuzh-tools-changedistiller-d7ceec136e94/resources/testdata/src_change/TestRight.java"};
    }

    @FXML
    private void initialize() {
        myButton.setOnAction((event) -> {
            String [] str = {beforeLink, afterLink};

            //String[] str = {"/Users/ashleychen/Desktop/REUSE/REUSE/sealuzh-tools-changedistiller-d7ceec136e94/resources/testdata/src_change/TestLeft.java", "/Users/ashleychen/Desktop/REUSE/REUSE/sealuzh-tools-changedistiller-d7ceec136e94/resources/testdata/src_change/TestRight.java"};
            //String[] str = {"@../testfiles/TestLeft.txt","@../testfiles/TestRight.txt"};
            ArrayList<String> al = Analyze.analyze(str);
            for(int i=0;i<al.size();i++){
                outputTextArea.appendText("\n"+ al.get(i).toString());
            }

        });


        beforeText.textProperty().addListener((observable, oldValue, newValue) -> {
            beforeLink = newValue;
        });

        afterText.textProperty().addListener((observable, oldValue, newValue) -> {
            afterLink = newValue;
        });



    }

}
