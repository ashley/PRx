package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import weka.classifiers.trees.RandomForest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
    @FXML private PieChart accuracyChart;
    @FXML private Button testDataButton;
    @FXML private Text p1text;
    @FXML private Text r1text;
    @FXML private Text f1text;
    @FXML private Text p2text;
    @FXML private Text r2text;
    @FXML private Text f2text;
    @FXML private Text p1Label;
    @FXML private Text f1Label;
    @FXML private Text r1Label;
    @FXML private Text p2Label;
    @FXML private Text f2Label;
    @FXML private Text r2Label;
    @FXML private Text correctResultText;
    @FXML private Text incorrectResultText;
    @FXML private ProgressBar compileProgressBar;
    @FXML private Text acceptedLabel;
    @FXML private Text rejectedLabel;
    @FXML private Text acceptedText;
    @FXML private Text rejectedText;


    private int fold;
    private String filePath;
    private double correct;
    private double incorrect;
    private String p1;
    private String r1;
    private String f1;
    private String p2;
    private String r2;
    private String f2;

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
                    correct = Double.parseDouble(al.get(0));
                    incorrect = Double.parseDouble(al.get(1));
                    NumberFormat nf = DecimalFormat.getInstance();
                    nf.setMaximumFractionDigits(2);
                    acceptedText.setText(nf.format(correct/(correct+incorrect)*100)+"%");
                    rejectedText.setText(nf.format(incorrect/(correct+incorrect)*100)+"%");
                    acceptedLabel.setVisible(true);
                    rejectedLabel.setVisible(true);
                    acceptedText.setVisible(true);
                    rejectedText.setVisible(true);
                    p1text.setText(al.get(2));
                    r1text.setText(al.get(3));
                    f1text.setText(al.get(4));
                    p2text.setText(al.get(5));
                    r2text.setText(al.get(6));
                    f2text.setText(al.get(7));
                    p1Label.setVisible(true);
                    f1Label.setVisible(true);
                    r1Label.setVisible(true);
                    p2Label.setVisible(true);
                    f2Label.setVisible(true);
                    r2Label.setVisible(true);
                    correctResultText.setVisible(true);
                    incorrectResultText.setVisible(true);
                    accuracyChart.setData(getChartData(correct, incorrect));
                    accuracyChart.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        });

        testDataButton.setOnAction((event) -> {
            File fi = new File("src/sample/testfiles/FilterData.txt");
            filePath = fi.getAbsolutePath();
            fileLabel.setText(filePath);
            fold = 10;
            foldsLabel.setText("10");
        });

    }

    private ObservableList<PieChart.Data> getChartData(double correct, double incorrect) {
        ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
        answer.addAll(new PieChart.Data("Correct", correct),
                new PieChart.Data("Incorrect", incorrect));
        return answer;
    }
}
