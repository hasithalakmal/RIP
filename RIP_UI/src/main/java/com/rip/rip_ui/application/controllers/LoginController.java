package com.rip.rip_ui.application.controllers;

import com.rip.rip_ui.application.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    
    private User user;
    
    @FXML
    private TextField usernameInput;
    
    @FXML
    private PasswordField passwordInput;
    
    @FXML
    private Label msgLabel;
    
    @FXML
    public void login(ActionEvent event) throws IOException {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        
        user = new User();
        if(user.authenticateUser(username, password)){
            msgLabel.setText("");
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage = new Stage();
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Application.fxml"));
         
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("RIP Application");
            stage.setScene(scene);
            stage.show();
            
        }else{
            msgLabel.setText("Invalid username or password");
        }
        
        //label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
