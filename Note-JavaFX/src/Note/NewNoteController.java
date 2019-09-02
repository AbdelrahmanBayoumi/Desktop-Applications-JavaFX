/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Note;

import app.HomeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class NewNoteController implements Initializable {

    @FXML
    private JFXTextArea text;

    private double xOffset = 0;
    private double yOffset = 0;

    private String hexaColor = "#FFF2AB";
    @FXML
    private AnchorPane AP;
    @FXML
    private JFXButton closeBTN;
    @FXML
    private JFXButton addBTN;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("NewNoteController -> initialize()  ...");
    }

    @FXML
    public void RootMousePressed(Event event) {
        MouseEvent e = (MouseEvent) event;
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }

    @FXML
    public void RootMouseDragged(Event event) {
        MouseEvent e = (MouseEvent) event;
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setX(e.getScreenX() - xOffset);
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setY(e.getScreenY() - yOffset);
    }

    @FXML
    private void closeAction(Event event) {
        System.out.println("NewNoteController -> closeAction()  ...");
        ((Stage) (text.getScene().getWindow())).close();
    }

    @FXML
    private void AddAction(Event event) {
        try {
            System.out.println("NewNoteController -> AddAction()  ...");
            HomeController.insert(text.getText(), hexaColor);
            closeAction(event);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void green(ActionEvent event) {
        hexaColor = "#A1EF9B";
        AP.setStyle("-fx-background-color : " + hexaColor + ";");
        addBTN.setStyle("-fx-background-color: #99ffcc");
        closeBTN.setStyle("-fx-background-color: #99ffcc");
    }

    @FXML
    private void blue(ActionEvent event) {
        hexaColor = "#9EDFFF";
        AP.setStyle("-fx-background-color : " + hexaColor);
        addBTN.setStyle("-fx-background-color: #99ccff");
        closeBTN.setStyle("-fx-background-color: #99ccff");
    }

    @FXML
    private void yellow(ActionEvent event) {
        hexaColor = "";
        AP.setStyle("-fx-background-color : " + hexaColor + ";");
        addBTN.setStyle("-fx-background-color: #ffffcc" + ";");
        closeBTN.setStyle("-fx-background-color: #ffffcc" + ";");
    }

    @FXML
    private void gray(ActionEvent event) {
        hexaColor = "#E0E0E0";
        AP.setStyle("-fx-background-color : " + hexaColor);
        addBTN.setStyle("-fx-background-color: #cccccc");
        closeBTN.setStyle("-fx-background-color: #cccccc");
    }

}
