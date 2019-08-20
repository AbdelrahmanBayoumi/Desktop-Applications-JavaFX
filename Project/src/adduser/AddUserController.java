package adduser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import static homepage.HomePageController.decorator1;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import project.DataBase;
import project.Paths;
import project.User;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class AddUserController implements Initializable {

    @FXML
    private JFXTextField UserNameField;
    @FXML
    private JFXPasswordField PasswordField;
    @FXML
    private JFXButton ButtonLogin;
    @FXML
    private ImageView key_pic_Login_Btn;
    @FXML
    private JFXToggleButton isAdminButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void NewUserAction(Event event) {
        if ("".equals(UserNameField.getText()) || "".equals(PasswordField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "", ButtonType.OK);
            alert.setHeaderText("Fill all fields ...");
            alert.setTitle("Error");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
            alert.showAndWait();
            return;
        }

        try {
            System.out.println("1");
            boolean AdminStatus = isAdminButton.isSelected();
            System.out.println("2");
            User user = new User(UserNameField.getText(), PasswordField.getText(), AdminStatus);
            System.out.println("3");
            boolean SaveUser = DataBase.SaveUser(user);
            System.out.println("4");
            if (!SaveUser) {
                System.out.println("5");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
                alert.setHeaderText("That username is taken. Try another.");
                alert.setTitle("Error");
                System.out.println("6");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                System.out.println("7");
                alert.showAndWait();
                System.out.println("8");
                return;
            }
            System.out.println("9");
            PasswordField.setText("");
            UserNameField.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            alert.setHeaderText("User added successfully");
            alert.setTitle("Notification");
            alert.showAndWait();
//            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            System.out.println("10");
        } catch (Exception e) {
            System.out.println("11111111");
            System.out.println(e);
            System.out.println(e);
            System.out.println(e);
            e.printStackTrace();
            System.out.println("77777777777777777777777777");
        }
    }

    @FXML
    private void isAdminButtonAction(ActionEvent event) {
        if (isAdminButton.isSelected()) {
            isAdminButton.setText("Admin");
        } else {
            isAdminButton.setText("Receptionist");
        }
    }

    @FXML
    public void goToUsers(Event event) {

        System.out.println("Users label clicked");
        try {
            //Load new FXML and assign it to scene
            Parent root = FXMLLoader.load(getClass().getResource(Paths.USERSVIEW));
            decorator1.setContent(root);
            decorator1.setTitle("Users");
            //set foucus in the window not in close and maximize button
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error load Users FXML !");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
