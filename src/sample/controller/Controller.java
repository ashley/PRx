package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import analyze.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Controller {

    @FXML private Button myButton;
    @FXML private Button leftButton;
    @FXML private Button rightButton;
    @FXML private TextArea outputTextArea;
    private String beforeLink;
    private String afterLink;

    public Controller(){
    }

    @FXML
    private void initialize() {
        myButton.setOnAction((event) -> {
            String [] str = {beforeLink, afterLink};
            boolean left = false;
            boolean right = false;
            File l = new File(beforeLink);
            if(l.exists() && !l.isDirectory()) {
                left = true;
            }
            else {
                System.out.println("Invalid BeforeLink");
            }
            File r = new File(afterLink);
            if(r.exists() && !r.isDirectory()) {
                right = true;
            }
            else {
                System.out.println("Invalid AfterLink");
            }
            //String[] str = {"/Users/ashleychen/Desktop/REUSE/REUSE/sealuzh-tools-changedistiller-d7ceec136e94/resources/testdata/src_change/TestLeft.java", "/Users/ashleychen/Desktop/REUSE/REUSE/sealuzh-tools-changedistiller-d7ceec136e94/resources/testdata/src_change/TestRight.java"};
            if(left && right) {
                ArrayList<String> al = Analyze.analyze(str);
                for (int i = 0; i < al.size(); i++) {
                    outputTextArea.appendText("\n" + al.get(i).toString());
                }
            }

        });

        leftButton.setOnAction((event) -> {
            Stage stage = Stage.class.cast(Control.class.cast(event.getSource()).getScene().getWindow());
            try {
                File fi = new FileChooser().showOpenDialog(stage);
                beforeLink = fi.getAbsolutePath();
            }catch (Exception e){
                System.out.print("Path selection canceled");
                e.printStackTrace();
            }
        });

        rightButton.setOnAction((event) -> {
            Stage stage = Stage.class.cast(Control.class.cast(event.getSource()).getScene().getWindow());
            try {
                File fi = new FileChooser().showOpenDialog(stage);
                afterLink = fi.getAbsolutePath();
            }catch (Exception e){
                System.out.print("Path selection canceled");
                e.printStackTrace();
            }
        });




    }

}
