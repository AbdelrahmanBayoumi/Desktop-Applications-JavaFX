package Help;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * @author Abdelrahman Bayoumi
 * @GitHub https://github.com/AbdelrahmanBayoumi 
 * @Email abdelrahmanbayoumi1@gmail.com
 */
public class HelpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("HelpController -> initialize() ...");
    }

    @FXML
    private void mailAction(MouseEvent event) throws URISyntaxException, IOException {
        Desktop desktop;
        if (Desktop.isDesktopSupported()
                && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
            URI mailto = new URI("mailto:abdelrahmanbayoumi1@gmail.com?");
            desktop.mail(mailto);
        } else {
            // TODO fallback to some Runtime.exec(..) voodoo?
            throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
        }
    }

    @FXML
    private void githubAction(MouseEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/AbdelrahmanBayoumi"));
        } catch (Exception e) {
            System.out.println("Error in URL");
        }
    }

    @FXML
    private void okAction(ActionEvent event) {
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).hide();
    }
}
