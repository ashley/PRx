package sample.controller;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.kohsuke.github.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RepoController  implements Initializable {

    @FXML private ListView myListView;

    private GHRepository repo;
    private List<String> commitList = new ArrayList<>();
    protected ListProperty<String> listProperty = new SimpleListProperty<>();

    public RepoController() throws IOException {
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Name: " + repo.getFullName());
        List<GHCommit> commits = repo.listCommits().asList();
        for(GHCommit commit:commits){
            commitList.add(commit.getSHA1());
        }
        myListView.itemsProperty().bind(listProperty);
        listProperty.set(FXCollections.observableArrayList(commitList));

    }

    @FXML
    public void getRepo() {

    }

    public void setupRepoController(GHRepository _repo){
        repo = _repo;
    }





}
