package sample.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.kohsuke.github.GHRepository;
import sample.controller.RepoController;

import java.io.IOException;

/**
 * Created by ashleychen on 11/30/16.
 */
public class Repo {

    private GHRepository repo;
    public void display(Button btn)throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Repo.fxml"));


        RepoController repoController = new RepoController();
        repoController.setupRepoController(repo);
        loader.setController(repoController);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 700);
        Stage currentStage = (Stage) btn.getScene().getWindow();
        currentStage.setTitle("PrX");
        currentStage.setScene(scene);
        currentStage.show();
    }

    public void setupRepo(GHRepository _repo){
        repo = _repo;
    }
}
