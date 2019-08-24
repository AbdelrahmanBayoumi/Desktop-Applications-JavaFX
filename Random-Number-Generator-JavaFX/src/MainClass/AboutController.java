/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClass;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class AboutController implements Initializable {

    //=======================
    private double xOffset = 0;
    private double yOffset = 0;
    //=======================
    @FXML
    private AnchorPane AP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void okAction(ActionEvent event) {
        appController.aboutDialog.close();
    }

    @FXML
    private void RootMousePressed(Event event) {
        MouseEvent e = (MouseEvent) event;
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }

    @FXML
    private void RootMouseDragged(Event event) {
        MouseEvent e = (MouseEvent) event;
        ((Stage) (AP.getScene().getWindow())).setX(e.getScreenX() - xOffset);
        ((Stage) (AP.getScene().getWindow())).setY(e.getScreenY() - yOffset);
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
}
