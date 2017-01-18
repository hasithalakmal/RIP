package com.rip.rip_ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RIPWizard.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("RIP Wizard");
        stage.setScene(scene);
        stage.show();
        
        stage.setMaximized(true);
    }

    @Override
    public void stop(){
        /* To be implemented */
    }
            
            
            
    public static void main(String[] args) {
        launch(args);
    }

}
