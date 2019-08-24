/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import project.Paths;
import static homepage.HomePageController.decorator1;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class UsersController implements Initializable {

    @FXML
    private ImageView key_pic_Login_Btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void NewUserAction(MouseEvent event) {
    }

    @FXML
    private void AddUser(ActionEvent event) {

        System.out.println("Add-User label clicked");
        try {
            //Load new FXML and assign it to scene
            Parent root = FXMLLoader.load(getClass().getResource(Paths.ADDUSERVIEW));
            decorator1.setContent(root);
            decorator1.setTitle("Add User");
            //set foucus in the window not in close and maximize button
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error load Add-User FXML !");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @FXML
    private void DeleteUser(ActionEvent event) {

        System.out.println("DeleteUser label clicked");
        try {
            //Load new FXML and assign it to scene
            Parent root = FXMLLoader.load(getClass().getResource(Paths.DELETEUSERVIEW));
            //create empty new stage

            decorator1.setContent(root);
            decorator1.setTitle("Delete User");

            //set foucus in the window not in close and maximize button
            root.requestFocus();

        } catch (Exception ex) {
            System.out.println("Error load DeleteUser FXML !");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @FXML
    private void ViewUsers(ActionEvent event) {

        System.out.println("ViewUsers label clicked");
        try {
            //Load new FXML and assign it to scene
            Parent root = FXMLLoader.load(getClass().getResource(Paths.VIEWUSERSVIEW));

            decorator1.setContent(root);
            decorator1.setTitle("View Users");

            //set foucus in the window not in close and maximize button
            root.requestFocus();

        } catch (Exception ex) {
            System.out.println("Error load ViewUsers FXML !");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
