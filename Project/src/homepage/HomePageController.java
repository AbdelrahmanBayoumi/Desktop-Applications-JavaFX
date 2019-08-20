package homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import hotel.Room;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static login.LoginController.decorator;
import static login.LoginController.window;
import project.DataBase;
import project.Paths;
import project.switchScreen;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class HomePageController implements Initializable {

    @FXML
    private JFXButton check_in_Buttton;
    @FXML
    private JFXButton check_out_Buttton;
    @FXML
    private JFXButton room_booking_Buttton;
    @FXML
    private JFXButton cancel_booking_Buttton;
    @FXML
    private Label usernameLabel11;
    @FXML
    private Label usernameLabel1;
    @FXML
    private Label usernameLabel111;
    @FXML
    private Label rank_Label;
    @FXML
    private ProgressIndicator available_par;
    @FXML
    private ProgressIndicator reserved_par;
    @FXML
    private JFXButton roomBTN;
    @FXML
    private JFXButton guestsBTN;
    //=============
    public static JFXDecorator  decorator1;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set userName label with the string in login 
      //  usernameLabel.setText(login.LoginController.user.getUsername());
        usernameLabel1.setText(login.LoginController.user.getUsername());

        if (login.LoginController.user.isIs_admin() == true) {
            rank_Label.setText("Admin");
        } else {
            rank_Label.setText("Receptionist");
        }

        List<Room> availableRooms = DataBase.getAvailableRooms();
        System.out.println("size =" + availableRooms.size());
        double available_Percentage = (availableRooms.size());
        available_par.setProgress(available_Percentage / 90);
        System.out.println(available_Percentage);
        double reserved_Percentage = (90 - availableRooms.size());
        System.out.println(reserved_Percentage);
        reserved_par.setProgress(reserved_Percentage / 90);

    }

    @FXML
    public void logout(Event event) {
        System.out.println("Log-Out label clicked");
        try {
            //Load new FXML and assign it to scene
            Parent root = FXMLLoader.load(getClass().getResource(Paths.LOGINVIEW));
            //create empty new stage
            Stage window2 = new Stage();
            //set layout properties
            JFXDecorator decorator = new JFXDecorator(window2, root, false, false, true);
            //Create new scene and add new layout in it
            Scene scene = new Scene(decorator);
            // add css file to the scene 
            String uri = getClass().getResource("dectaorStyle.css").toExternalForm();
            scene.getStylesheets().add(uri);
            //stage properties
            int width = 690, height = 620;
            window2.setScene(scene);
            window2.setMaxHeight(height);
            window2.setMinHeight(height);
            window2.setMaxWidth(width);
            window2.setMinWidth(width);
            Image icon = new Image(getClass().getResourceAsStream("/img/login_icon.png"));
            window2.getIcons().add(icon);
            window2.show();
            //set foucus in the window not in close and maximize button
            root.requestFocus();
            //close the old stage
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

        } catch (Exception ex) {
            System.out.println("Error load login FXML !");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void goTO_check_in(Event event) {
        System.out.println("Check in button clicked");

        try {
            //load FXML File in Parent 
            Parent root = FXMLLoader.load(getClass().getResource(Paths.CHECKINVIEW));
            // get the stage to change the title
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Check in");
            //change the content in the decorator with the new root
            decorator.setContent(root);
            // set Title to the new decorator
            decorator.setTitle("Check in");
            //set foucus in the window not in close and maximize button
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error load Checkin FXML !");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void goTo_room_booking(Event event) {
        System.out.println("Room Booking button clicked");

        try {
            //load FXML File in Parent 
            Parent root = FXMLLoader.load(getClass().getResource(Paths.ROOMBOOKINGVIEW));
            // get the stage to change the title
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Room Booking");
            //change the content in the decorator with the new root
            decorator.setContent(root);
            // set Title to the new decorator
            decorator.setTitle("Room Booking");
            //set foucus in the window not in close and maximize button
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error load RoomBooking FXML !");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void goTo_cancel_booking(Event event) {
        System.out.println("Cancel Booking button clicked");

        try {
            //load FXML File in Parent 
            Parent root = FXMLLoader.load(getClass().getResource(Paths.CANCELBOOKINGVIEW));
            // get the stage to change the title
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Cancel Booking");
            //change the content in the decorator with the new root
            decorator.setContent(root);
            // set Title to the new decorator
            decorator.setTitle("Cancel Booking");
            //set foucus in the window not in close and maximize button
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error load CancelBooking FXML !");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void goTo_Check_out(Event event) {
        System.out.println("Check out button clicked");

        try {
            //load FXML File in Parent 
            Parent root = FXMLLoader.load(getClass().getResource(Paths.CHECKOUTVIEW));
            // get the stage to change the title
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Check out");
            //change the content in the decorator with the new root
            decorator.setContent(root);
            // set Title to the new decorator
            decorator.setTitle("Check out");
            //set foucus in the window not in close and maximize button
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error load Checkout FXML !");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void goTo_Users(Event event) {
        if (login.LoginController.user.isIs_admin()) {
            System.out.println("Users label clicked");
            try {
                //Load new FXML and assign it to scene
                Parent root = FXMLLoader.load(getClass().getResource(Paths.USERSVIEW));
                //create empty new stage
                Stage window2 = new Stage();
                //set layout properties
                decorator1 = new JFXDecorator(window2, root, false, false, true);
                //Create new scene and add new layout in it
                Scene scene = new Scene(decorator1);
                // add css file to the scene 
                String uri = getClass().getResource("dectaorStyle.css").toExternalForm();
                scene.getStylesheets().add(uri);
                //stage properties
                int width = 690, height = 620;
                window2.setScene(scene);
                window2.setMaxHeight(height);
                window2.setMinHeight(height);
                window2.setMaxWidth(width);
                window2.setMinWidth(width);
                Image icon = new Image(getClass().getResourceAsStream("/img/login_icon.png"));
                window2.getIcons().add(icon);
                window2.show();
                //set foucus in the window not in close and maximize button
                root.requestFocus();
            } catch (Exception ex) {
                System.out.println("Error load Users FXML !");
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
            alert.setHeaderText("Only Admins Can Edit Users");
            alert.setTitle("Error");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
            alert.showAndWait();
        }
    }

    @FXML
    public void goTo_Rooms(Event event) {
        System.out.println("Rooms button clicked");

        try {
            //load FXML File in Parent 
            Parent root = FXMLLoader.load(getClass().getResource(Paths.ROOMSVIEW));
            // get the stage to change the title
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Rooms");
            //change the content in the decorator with the new root
            decorator.setContent(root);
            // set Title to the new decorator
            decorator.setTitle("Rooms");
            //set foucus in the window not in close and maximize button
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error load Rooms FXML !");
            switchScreen.Load_Error_Alert("Rooms",ex.getMessage());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void goTo_Guests(Event event) {
        
        System.out.println("Rooms button clicked");

        try {
            //load FXML File in Parent 
            Parent root = FXMLLoader.load(getClass().getResource(Paths.GUESTSVIEW));
            // get the stage to change the title
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Guests");
            //change the content in the decorator with the new root
            decorator.setContent(root);
            // set Title to the new decorator
            decorator.setTitle("Guests");
            //set foucus in the window not in close and maximize button
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error load Guests FXML !");
            switchScreen.Load_Error_Alert("Guests",ex.getMessage());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
    }
}
