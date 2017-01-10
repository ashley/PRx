package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.kohsuke.github.GHEmail;
import org.kohsuke.github.GitHub;
import sample.application.Tree;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {

    @FXML TextField username = new TextField();
    @FXML PasswordField password = new PasswordField();
    @FXML Button loginButton = new Button();
    @FXML Label check = new Label();

    private String user;
    private String pass;
    private GitHub github;
    private String loginValid = null;
    private boolean login = false;

    public LoginController() {
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("LoginController Initialized");

        loginButton.setOnAction((event) -> {
            String [] str = {user, pass};
            try {
                login = github(str[0],str[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            GHEmail email = new GHEmail();
            check.setText(loginValid);
            if (login){
                try{
                    Tree tree = new Tree();
                    tree.setupTree(github);
                    System.out.println(github.toString());
                    tree.display(loginButton);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        });


        username.textProperty().addListener((observable, oldValue, newValue) -> {
            user = newValue;
        });

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            pass = newValue;
        });


    }

    private boolean github(String user, String pass) throws IOException {
        github = GitHub.connectUsingPassword(user,pass);
        //System.out.println(github.getRateLimit());
        try {
            github.getMyself();
            loginValid = "Login Success";
            return true;
        }
        catch (IllegalStateException e){ //Null login
            loginValid = "Please enter something";
            return false;
        }
        catch(IOException e){ //Incorrect authorization or rate limit
            loginValid = "Invalid username or password";
            return false;
        }

    }

    public GitHub getGit(){
        return github;
    }




}
