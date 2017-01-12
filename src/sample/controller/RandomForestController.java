package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import weka.classifiers.trees.RandomForest;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by ashleychen on 1/7/17.
 */
public class RandomForestController implements Initializable {

    @FXML private Button filePathButton;
    @FXML private Button compileButton;
    @FXML private Slider foldsSlider;
    @FXML private Text foldsLabel;
    @FXML private Text fileLabel;
    @FXML private TextArea outputText;

    private int fold;
    private String filePath;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("RandomForestController Initialized");
        filePathButton.setOnAction((event) -> {
            Stage stage = Stage.class.cast(Control.class.cast(event.getSource()).getScene().getWindow());
            try {
                FileChooser fileChooser = new FileChooser();
                // Set extension filter
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                File fi = fileChooser.showOpenDialog(stage);
                filePath = fi.getAbsolutePath();
                fileLabel.setText(filePath);
            }catch (Exception e){
                System.out.print("Path selection canceled");
            }
        });

        foldsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            fold = newValue.intValue();
            foldsLabel.setText(Integer.toString(fold));
        });

        compileButton.setOnAction((event) -> {
            String [] str = {filePath, Integer.toString(fold)};
            boolean file = false;
            File fi = new File(filePath);
            if(fi.exists() && !fi.isDirectory()) {
                file = true;
            }
            else {
                System.out.println("Invalid file path");
            }
            if(file) {
                try {
                    ArrayList<String> al = UI.RandomForestExecute.main(str);
                    for (int i = 0; i < al.size(); i++) {
                        outputText.appendText("\n" + al.get(i).toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        });

    }
}
