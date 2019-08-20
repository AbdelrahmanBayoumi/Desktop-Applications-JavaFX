/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewusers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import project.DataBase;
import project.User;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class ViewUsersController implements Initializable {

    @FXML
    private TableColumn<User, String> c1;
    @FXML
    private TableColumn<User, String> c2;
    @FXML
    private TableColumn<User, String> c3;
    @FXML
    private TableView<User> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c1.setCellValueFactory(new PropertyValueFactory("username"));
        c2.setCellValueFactory(new PropertyValueFactory("password"));
        c3.setCellValueFactory(new PropertyValueFactory("is_admin"));
        List<User> users = DataBase.getUsers();
        table.getItems().addAll(users);
    }    

   

    @FXML
    private void goToUsers(Event event) {
        new adduser.AddUserController().goToUsers(event);
    }

    
}
