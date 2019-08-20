package login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.DataBase;
import project.Paths;
import project.User;
import project.switchScreen;

public class LoginController implements Initializable {

    //=======================
    public static Stage window;
    public static JFXDecorator decorator;
    public static User user;
    //=======================
    @FXML
    private JFXButton ButtonLogin;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXTextField UserNameField;
    @FXML
    private JFXPasswordField PasswordField;
    @FXML
    private ImageView key_pic_Login_Btn;
    //=======================

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void loginAction(Event event) {
        try {
            DataBase.CheckConnection();
            System.out.println("CheckConnection true");
        } catch (Exception e) {
            System.out.println("Error in CheckConnection()");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Connection Error");
            alert.setContentText("Error while trying to connect to database ..");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/WARNING_PNG.png"));
            alert.show();
            return;
        }

        user = new User(UserNameField.getText(), PasswordField.getText(), false);
        boolean userIsAdmin = User.isUserAdmin(user);
        user.setIs_admin(userIsAdmin);

        try {
            if (User.isUserValid(user)) {
                goToHomePage(event);
            } else {
                try {
                    System.out.println("Executing popup window");
                    new switchScreen().popUp(event, "/login/ErrorPopUp.fxml", 370, 250, "", "/img/Error01.png");
                } catch (Exception ex) {
                    System.out.println("error on popUp window");
                    System.out.println(ex);
                }
            }
        } catch (Exception e) {
            System.out.println("isUserValid Exception");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Connection Error");
            alert.setContentText("Error while trying to connect to database ..");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
            alert.show();
            return;
        }

    }

    public void goToHomePage(Event event) {
        try {
            //Load new FXML and assign it to scene
            Parent root = FXMLLoader.load(getClass().getResource(Paths.HOMEPAGEVIEW));
            //create empty new stage
            window = new Stage();
            //set layout properties
            decorator = new JFXDecorator(window, root, false, false, true);
            //Create new scene and add new layout in it
            int width = 1400, height = 1000;
            Scene scene = new Scene(decorator, width, height);
            // set Title to the new decorator
            decorator.setTitle("Hotel Reservation System");
            //set the stage properties
            String uri = getClass().getResource("dectaorStyle.css").toExternalForm();
            scene.getStylesheets().add(uri);
            window.setScene(scene);
            window.setMaxHeight(height);
            window.setMinHeight(height);
            window.setMaxWidth(width);
            window.setMinWidth(width);
            window.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.jpg")));
            window.setTitle("Hotel System");
            //show homePage stage
            window.show();
            //set foucus in the window not in close and maximize button
            root.requestFocus();
            //close login stage
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException ex) {
            System.out.println("Error load homePage FXML !");
            System.out.println(ex);

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
