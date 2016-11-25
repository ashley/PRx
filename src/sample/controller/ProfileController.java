package sample.controller;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController  implements Initializable {

    @FXML private Button button;
    @FXML private Label label;
    @FXML private ListView myListView;

    private GHMyself you;
    protected List<String> asianCurrencyList = new ArrayList<>();
    protected List<String> europeanCurrencyList = new ArrayList<>();
    protected ListProperty<String> listProperty = new SimpleListProperty<>();

    public ProfileController() throws IOException {
    }

    public void initialize(URL location, ResourceBundle resources) {
        //Need to connect to GitHub from LoginController. Except how?
        PagedIterable<GHRepository> repos = you.listRepositories();
        System.out.println(repos);
        int count = 0;
        while(repos.iterator().hasNext() && count < 5){
            GHRepository repo = repos.iterator().next();
            System.out.println(repo.getFullName());
            count++;
        }
        /*for(GHRepository repo:repos){
            asianCurrencyList.add(repo.getFullName());
        }*/
        asianCurrencyList.add("GHC");
        myListView.itemsProperty().bind(listProperty);
        listProperty.set(FXCollections.observableArrayList(asianCurrencyList));

    }

    @FXML
    public void getRepo() {

    }





}
