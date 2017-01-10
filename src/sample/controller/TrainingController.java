package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Created by ashleychen on 1/7/17.
 */
public class TrainingController {
    @FXML private Button trainingImportButton;
    @FXML private Button entropyModelButton;
    @FXML private Button entropyComputeButton;
    @FXML private Button entropyTestButton;
    @FXML private Button trainComputerButton;
    @FXML private Button trainingDestinationButton;
    @FXML private ListView trainList;
    @FXML private ListView entropyList;
    @FXML private Slider iterationSlider;
    @FXML private RadioButton crossEntropyRadio;
    @FXML Text iterationLabel;


    private String trainingImportLink;
    private String trainingDestinationLink;
    private int iteration;


    @FXML
    private void initalize(){
        System.out.println("TrainingController Initialized");
        trainingImportButton.setOnAction((event) -> {
            Stage stage = Stage.class.cast(Control.class.cast(event.getSource()).getScene().getWindow());
            try {
                FileChooser fileChooser = new FileChooser();
                // Set extension filter
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                File fi = fileChooser.showOpenDialog(stage);
                trainingImportLink = fi.getAbsolutePath();
            }catch (Exception e){
                System.out.print("Path selection canceled");
            }
        });

        trainingDestinationButton.setOnAction((event) -> {
            Stage stage = Stage.class.cast(Control.class.cast(event.getSource()).getScene().getWindow());
            try {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(stage);
                trainingDestinationLink = selectedDirectory.getAbsolutePath();
            }catch (Exception e){
                System.out.print("Path selection canceled");
            }
        });

        iterationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            iteration = newValue.intValue();
        });

        trainComputerButton.setOnAction((event) -> {
            iterationLabel.setText(Integer.toString(iteration));
        });
    }
}
