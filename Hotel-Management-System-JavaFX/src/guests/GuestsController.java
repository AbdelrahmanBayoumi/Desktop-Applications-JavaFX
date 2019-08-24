/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guests;

import com.jfoenix.controls.JFXButton;
import hotel.Guest;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.DataBase;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class GuestsController implements Initializable {

    @FXML
    private Label backLabel;
    @FXML
    private JFXButton Logobtn;
    @FXML
    private Label usernameLabel;
    @FXML
    private Hyperlink logoutLink;
    @FXML
    private TableView<Guest> tabel2;
    @FXML
    private TableColumn<Guest, String> c1;
    @FXML
    private TableColumn<Guest, String> c2;
    @FXML
    private TableColumn<Guest, String> c3;
    @FXML
    private TableColumn<Guest, String> c4;
    @FXML
    private TableColumn<Guest, String> c5;
    @FXML
    private TableColumn<Guest, String> c6;
    @FXML
    private TableColumn<Guest, String> c7;
    @FXML
    private TableColumn<Guest, String> c8;
    @FXML
    private TableColumn<Guest, String> c9;
    @FXML
    private TableColumn<Guest, String> c10;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Guest> guests = DataBase.getGuests();

        for (int i = 0; i < guests.size(); i++) {
            System.out.println("aa" + guests.get(i));
        }
        c1.setCellValueFactory(new PropertyValueFactory("roomID"));
        c2.setCellValueFactory(new PropertyValueFactory("Name"));
        c3.setCellValueFactory(new PropertyValueFactory("Email"));
        c4.setCellValueFactory(new PropertyValueFactory("Address"));
        c5.setCellValueFactory(new PropertyValueFactory("city"));
        c6.setCellValueFactory(new PropertyValueFactory("Nationality"));
        c7.setCellValueFactory(new PropertyValueFactory("passportNumber"));
        c8.setCellValueFactory(new PropertyValueFactory("phoneNo"));
        c9.setCellValueFactory(new PropertyValueFactory("numberOfDaysStayed"));
        c10.setCellValueFactory(new PropertyValueFactory("Fees"));
        try {
            tabel2.getItems().addAll(guests);
        } catch (Exception e) {
            e.printStackTrace();
        }
        usernameLabel.setText(login.LoginController.user.getUsername());

    }

    @FXML
    private void logout(Event event) {
        new homepage.HomePageController().logout(event);
    }

    @FXML
    public void goToHomePage(Event event) {
        new checkin.CheckInController().goToHomePage(event);
    }

    @FXML
    private void goTO_check_in(ActionEvent event) {
        new homepage.HomePageController().goTO_check_in(event);
    }

    @FXML
    private void goTo_room_booking(ActionEvent event) {
        new homepage.HomePageController().goTo_room_booking(event);
    }

    @FXML
    private void goTo_cancel_booking(ActionEvent event) {
        new homepage.HomePageController().goTo_cancel_booking(event);
    }

    @FXML
    private void goTO_check_out(Event event) {
        new homepage.HomePageController().goTo_Check_out(event);
    }

    @FXML
    private void goto_Rooms(ActionEvent event) {
        new homepage.HomePageController().goTo_Rooms(event);
    }

    @FXML
    private void goTo_guests(ActionEvent event) {
        new homepage.HomePageController().goTo_Guests(event);
    }
}
