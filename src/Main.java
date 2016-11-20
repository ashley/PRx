import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.application.Login;

public class Main extends Application {
    private int knob = 1;

    @Override
    public void start(Stage primaryStage) throws IOException {
        if (knob==1){
            Login login = new Login();
            login.display();
        }
        else{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/sample/view/UI.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setTitle("TitleName");
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
