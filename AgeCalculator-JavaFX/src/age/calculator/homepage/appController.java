package age.calculator.homepage;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class appController implements Initializable {

    //=======================
    private double xOffset = 0;
    private double yOffset = 0;
    //=======================
    @FXML
    private StackPane AP;
    @FXML
    private Label daysLabel;
    @FXML
    private Label monthLabel;
    @FXML
    private Label yearLable;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu file;
    @FXML
    private MenuItem About;
    @FXML
    private MenuItem close;
    @FXML
    private JFXDatePicker birthDatePicker;
    @FXML
    private JFXDatePicker currentDatePicker;

    public static JFXDialog aboutDialog;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentDatePicker.setValue(LocalDate.now());
        try {
            AnchorPane aboutPane = FXMLLoader.load(getClass().getResource("/age/calculator/about/about.fxml"));
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
        System.exit(0);
    }

    private boolean isMaximized() {
        Stage s = ((Stage) (AP.getScene().getWindow()));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        return s.getWidth() == bounds.getWidth() && s.getHeight() == bounds.getHeight();
    }

    @FXML
    private void MaxWindow(Event event) {
        Stage s = ((Stage) (AP.getScene().getWindow()));
        if (isMaximized()) {
            s.setWidth(600);
            s.setHeight(600);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            s.setX(bounds.getWidth() / 2 - (600 / 2));
            s.setY(bounds.getHeight() / 2 - (600 / 2));
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
    private void AP_keyListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            Stage s = ((Stage) (AP.getScene().getWindow()));
            if (isMaximized()) {
                MaxWindow(event);
            }
        }
    }

    @FXML
    private void CalcAction(ActionEvent event) {
        try {
            LocalDate birthDate = birthDatePicker.getValue();
            LocalDate currentDate = currentDatePicker.getValue();
            Period period = Period.between(birthDate, currentDate);
            if (period.getDays() < 0) {
                showErrorAlert();
                resetData();
                return;
            }
            daysLabel.setText(String.valueOf(period.getDays()));
            monthLabel.setText(String.valueOf(period.getMonths()));
            yearLable.setText(String.valueOf(period.getYears()));
        } catch (Exception e) {
            showErrorAlert();
            resetData();
        }
    }

    @FXML
    private void aboutWidow(ActionEvent event) {
        if (aboutDialog.isVisible()) {
            return;
        }
        aboutDialog.show();
    }

    private void showErrorAlert() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Enter Correct Date !");
        a.showAndWait();
    }

    private void resetData() {
        daysLabel.setText("");
        monthLabel.setText("");
        yearLable.setText("");
    }
}
