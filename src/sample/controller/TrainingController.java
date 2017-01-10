package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ashleychen on 1/7/17.
 */
public class TrainingController implements Initializable {
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
    @FXML private Text iterationLabel;


    private String trainingImportLink;
    private String trainingDestinationLink;
    private int iteration;
    private boolean crossEntropyToggle;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
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
            iterationLabel.setText(Integer.toString(iteration));
        });

    }
}
