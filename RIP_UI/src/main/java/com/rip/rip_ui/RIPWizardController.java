package com.rip.rip_ui;

import com.rip.rip_ui.api.ProjectHandler;
import com.rip.rip_ui.api.impl.ProjectHandlerImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RIPWizardController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button newProjectBtn;

    @FXML
    private Button loadFromDiskBtn;

    @FXML
    private Button loadFromServerBtn;

    @FXML
    private Button saveProjectBtn;

    @FXML
    private Button editProjectDetailsBtn;

    @FXML
    private Button addTableBtn;

    @FXML
    private Button addResourceBtn;

    @FXML
    private Button addFKBtn;

    private ProjectHandler projectHandler;

    //is the current project saved or not
    private boolean isProjectSaved = true;

    private boolean isProjectCreated = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.projectHandler = new ProjectHandlerImpl();

    }

    @FXML
    private void createProject() {
        if (projectHandler.createProject()) {
            borderPane.setCenter(projectHandler.getScrollPane());
        }
    }

    @FXML
    public void loadFromDisk() {
        /* To be implemented */
    }

    @FXML
    public void loadFromServer() {
        /* To be implemented */
    }

    @FXML
    public void saveProject() {
        projectHandler.saveProject();

    }

    @FXML
    public void editProjectDetails() {
        projectHandler.editProject();
    }

    @FXML
    public void addTable() {
        projectHandler.addTable();
    }

    @FXML
    public void addResource() {
        projectHandler.addResource();
    }

    @FXML
    public void addForeignKey() {
        projectHandler.addForeignKey();
    }

    public void exitApplication() {
    }

}
