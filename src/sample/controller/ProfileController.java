package sample.controller;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;
import sample.application.Profile;
import sample.application.Repo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController  implements Initializable {

    @FXML private Button button;
    @FXML private Label label;
    @FXML private ListView myListView;

    private GitHub gitHub;
    private GHMyself you;
    private GHRepository pickedRepo;
    private PagedIterable<GHRepository> repos = null;
    protected List<String> repoList = new ArrayList<>();
    protected ListProperty<String> listProperty = new SimpleListProperty<>();

    public ProfileController() throws IOException {
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            repos = gitHub.getMyself().listRepositories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(GHRepository repo:repos){
            repoList.add(repo.getName());
        }
        myListView.itemsProperty().bind(listProperty);
        listProperty.set(FXCollections.observableArrayList(repoList));

        button.setOnAction((event) -> {
            try{
                Repo repo = new Repo();
                System.out.println(pickedRepo.getFullName() + " In Controller");
                repo.setupRepo(pickedRepo);
                repo.display(button);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });

    }

    @FXML
    public void getRepo() {

    }

    @FXML public void handleMouseClick(MouseEvent arg0) throws IOException {
        System.out.println("clicked on " + myListView.getSelectionModel().getSelectedItem());
        pickedRepo = gitHub.getMyself().getRepository(myListView.getSelectionModel().getSelectedItem().toString());
    }

    public void setupProfileController(GitHub _github){
        gitHub = _github;
    }





}
