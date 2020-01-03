package controllers;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {
    
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TextField UpperField;
    @FXML
    private TextField LowerField;
    @FXML
    private TextField inputField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public boolean isMaximized(Event event) {
        Stage s = ((Stage) (((Node) (event.getSource())).getScene().getWindow()));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        return s.getWidth() == bounds.getWidth() && s.getHeight() == bounds.getHeight();
    }
    
    @FXML
    public void MaxWindow(Event event) {
        Stage s = ((Stage) (((Node) (event.getSource())).getScene().getWindow()));
        if (isMaximized(event)) {
            s.setWidth(610);
            s.setHeight(361);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            s.setX(bounds.getWidth() / 2 - (610 / 2));
            s.setY(bounds.getHeight() / 2 - (361 / 2));
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
    public void MinWindow(Event event) {
        Stage s = ((Stage) (((Node) (event.getSource())).getScene().getWindow()));
        s.setIconified(true);
    }
    
    @FXML
    public void RootMousePressed(Event event) {
        MouseEvent e = (MouseEvent) event;
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }
    
    @FXML
    public void RootMouseDragged(Event event) {
        if (isMaximized(event)) {
            return;
        }
        MouseEvent e = (MouseEvent) event;
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setX(e.getScreenX() - xOffset);
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setY(e.getScreenY() - yOffset);
    }
    
    @FXML
    private void NameAction(Event event) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/AbdelrahmanBayoumi"));
        } catch (Exception e) {
            System.out.println("Error in URL");
        }
    }
    
    @FXML
    private void closeWindow(Event event) {
        System.exit(0);
    }
    
    @FXML
    private void keyPressedAction(KeyEvent event) {
        UpperField.setText(inputField.getText().toUpperCase());
        LowerField.setText(inputField.getText().toLowerCase());
    }
    
    private void changeStylesheets(String uri, Event event) {
        ((Node) (event.getSource())).getScene().getStylesheets().remove(1);
        ((Node) (event.getSource())).getScene().getStylesheets().add(
                getClass().getResource(uri).toExternalForm());
    }
    int counter = 0;
    
    @FXML
    private void changeTheme(Event event) {
        switch (counter) {
            case 0:
                System.out.println("dark");
                changeStylesheets("/resources/dark-theme.css", event);
                break;
            case 1:
                System.out.println("blue");
                changeStylesheets("/resources/blue-theme.css", event);
                break;
            case 2:
                System.out.println("green");
                changeStylesheets("/resources/green-theme.css", event);
                break;
            case 3:
                System.out.println("red");
                changeStylesheets("/resources/red-theme.css", event);
                break;
        }
        counter = ++counter % 4;
    }
}
