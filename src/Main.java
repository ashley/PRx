import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.application.Login;
import sample.controller.TestingTabController;

public class Main extends Application {
    private int knob = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {
        if (knob==1){
            Login login = new Login();
            login.display();
        }
        else{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/sample/view/TestingTab.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setTitle("PrX");
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
