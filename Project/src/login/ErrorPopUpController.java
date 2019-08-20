package login;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ErrorPopUpController implements Initializable {

    @FXML
    private Label messageLabel;
    @FXML
    private JFXButton okbtn;
    @FXML
    private Label messageLabel1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void closePopUpAction(ActionEvent event) {
        okbtn.getScene().getWindow().hide();
    }
    
}
