/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClass;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author UpToDate
 */
public class appController implements Initializable {

    //=======================
    private double xOffset = 0;
    private double yOffset = 0;
    //=======================
    @FXML
    private StackPane AP;
    @FXML
    private TextField ResultField;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu file;
    @FXML
    private MenuItem About;
    @FXML
    private MenuItem close;
    @FXML
    private JFXTextField MinTextField;
    @FXML
    private JFXTextField MaxTextField;

    public static JFXDialog aboutDialog;
    StackPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // about 
        try {
            AnchorPane aboutPane = FXMLLoader.load(getClass().getResource("about.fxml"));
            aboutDialog = new JFXDialog(AP, aboutPane, JFXDialog.DialogTransition.TOP);
        } catch (IOException ex) {
            System.out.println("error in loading about fxml");
        }
    }

    @FXML
    private void RootMousePressed(Event event) {
        if (isMaximized()) {
            return;
        }
        MouseEvent e = (MouseEvent) event;
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }

    @FXML
    private void RootMouseDragged(Event event) {
        if (isMaximized()) {
            return;
        }
        MouseEvent e = (MouseEvent) event;
        ((Stage) (AP.getScene().getWindow())).setX(e.getScreenX() - xOffset);
        ((Stage) (AP.getScene().getWindow())).setY(e.getScreenY() - yOffset);
    }

    @FXML
    private void closeWindow(Event event) {
        System.out.println("closeWindow ...");
        System.exit(0);
    }

    @FXML
    private void aboutWidow(ActionEvent event) {
        if (aboutDialog.isVisible()) {
            return;
        }
        aboutDialog.show();
    }

    private boolean isMaximized() {
        Stage s = ((Stage) (AP.getScene().getWindow()));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        if (s.getWidth() == bounds.getWidth() && s.getHeight() == bounds.getHeight()) {
            return true;
        }
        return false;
    }

    @FXML
    private void MaxWindow(Event event) {
        Stage s = ((Stage) (AP.getScene().getWindow()));
        if (isMaximized()) {
            s.setWidth(400);
            s.setHeight(400);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            s.setX(bounds.getWidth() / 2 - (200));
            s.setY(bounds.getHeight() / 2 - (200));
        } else {
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            s.setWidth(bounds.getWidth());
            s.setHeight(bounds.getHeight());
            s.setX(0);
            s.setY(0);
        }
    }

    @FXML
    private void MinWindow(Event event) {
        Stage s = ((Stage) (AP.getScene().getWindow()));
        s.setIconified(true);
        AP.requestFocus();
    }

    @FXML
    private void AP_keyListener(KeyEvent event) {
        System.out.println("AP keyListener ..." + event.getCode().getName());
        if (event.getCode() == KeyCode.ESCAPE) {
            System.out.println("ESCAPE pressed ...");
            Stage s = ((Stage) (AP.getScene().getWindow()));
            if (isMaximized()) {
                MaxWindow(event);
            }
        }
    }

    @FXML
    private void GenerateAction(ActionEvent event) {
        if ("".equals(MaxTextField.getText().trim()) && "".equals(MinTextField.getText().trim())) {
            long random = ((long) (Math.random() * (1000 - 0 + 1)) + 0);
            ResultField.setText("" + random);
            return;
        } else if ("".equals(MaxTextField.getText().trim()) || "".equals(MinTextField.getText().trim())) {
            ResultField.setText("");
            return;
        }
        long min, max;
        try {
            max = Long.valueOf(MaxTextField.getText());
            min = Long.valueOf(MinTextField.getText());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("error in parsing");
            ResultField.setText("Enter correct value");
            return;
        }
        long random = ((long) (Math.random() * (max - min + 1)) + min);
        ResultField.setText(random + "");
    }

}
