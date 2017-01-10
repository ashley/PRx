package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import analyze.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kohsuke.github.GitHub;
import sample.application.Profile;
import sample.application.Tree;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EditorController {

    @FXML private Button myButton;
    @FXML private Button leftButton;
    @FXML private Button rightButton;
    @FXML private Button testFilesButton;
    @FXML private TextArea outputTextArea;
    @FXML private TextArea leftTextArea;
    @FXML private TextArea rightTextArea;

    private GitHub gitHub;
    private String beforeLink;
    private String afterLink;

    public EditorController(){
    }

    @FXML
    private void initialize() {
        System.out.print("EditorController Initialized");

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
                FileChooser fileChooser = new FileChooser();
                // Set extension filter
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                File fi = fileChooser.showOpenDialog(stage);
                beforeLink = fi.getAbsolutePath();
                System.out.println(beforeLink);
                String content = readFile(beforeLink, StandardCharsets.UTF_8);
                leftTextArea.setText(content);

            }catch (Exception e){
                System.out.print("Path selection canceled");
                e.printStackTrace();
            }
        });

        testFilesButton.setOnAction((event) -> {
            File left = new File("src/sample/testfiles/TestLeft.txt");
            beforeLink = left.getAbsolutePath();
            try {
                System.out.println(beforeLink);
                leftTextArea.setText(readFile(beforeLink, StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
            File right = new File("src/sample/testfiles/TestRight.txt");
            afterLink = right.getAbsolutePath();
            try {
                System.out.println(afterLink);
                rightTextArea.setText(readFile(afterLink, StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        rightButton.setOnAction((event) -> {
            Stage stage = Stage.class.cast(Control.class.cast(event.getSource()).getScene().getWindow());
            try {
                FileChooser fileChooser = new FileChooser();
                // Set extension filter
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                File fi = fileChooser.showOpenDialog(stage);
                afterLink = fi.getAbsolutePath();
                rightTextArea.setText(readFile(afterLink, StandardCharsets.UTF_8));
            }catch (Exception e){
                System.out.print("Path selection canceled");
            }
        });
    }

    public void setupController(GitHub _gitHub){
        gitHub = _gitHub;
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
