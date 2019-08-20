/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deleteuser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.DataBase;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class DeleteUserController implements Initializable {

    @FXML
    private JFXTextField UserNameField;
    @FXML
    private JFXButton ButtonLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("DeleteUserController ...");
    }

    @FXML
    private void DeleteAction(ActionEvent event) {
        if ("".equals(UserNameField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "", ButtonType.OK);
            alert.setHeaderText("Fill all fields ...");
            alert.setTitle("Error");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/WARNING_PNG.png"));
            alert.showAndWait();
            return;
        }
        if(UserNameField.getText().equals(login.LoginController.user.getUsername())){
            Alert alert = new Alert(Alert.AlertType.WARNING, "", ButtonType.OK);
            alert.setHeaderText("You cannot Delete yourself !");
            alert.setTitle("Error");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/WARNING_PNG.png"));
            alert.showAndWait();
            return;
        }
        boolean DeleteUser = DataBase.DeleteUser(UserNameField.getText());
        if (DeleteUser) {
            UserNameField.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            alert.setHeaderText("User Deleted successfully");
            alert.setTitle("Notification");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
            alert.setHeaderText("User-Name not valid, Tyr Agian");
            alert.setTitle("Error");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
            alert.showAndWait();
        }

    }

    @FXML
    private void goToUsers(Event event) {
        new adduser.AddUserController().goToUsers(event);
    }

}
