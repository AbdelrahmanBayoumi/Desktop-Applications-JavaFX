/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agecalcapp;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
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
            s.setWidth(600);
            s.setHeight(600);
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

    public int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public Date LocalDate_TO_Date(LocalDate ld) {
        Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date res = Date.from(instant);
        return res;
    }

    public LocalDate Date_TO_LocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDate res = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return res;
    }

    @FXML
    private void CalcAction(ActionEvent event) {
        Date birthDate, currentDate;

        try {
            birthDate = LocalDate_TO_Date(birthDatePicker.getValue());
            currentDate = LocalDate_TO_Date(currentDatePicker.getValue());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Enter Correct Date !");
            a.showAndWait();
            return;
        }
        int days = getDifferenceDays(birthDate, currentDate);
        if (days < 0) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Enter Correct Date !");
            a.showAndWait();
            return;
        }
        int years = days / 365;
        days -= (years * 365);
        int months = days / 30;
        days -= (months * 30);
        daysLabel.setText(days + "");
        monthLabel.setText(months + "");
        yearLable.setText(years + "");
    }

    @FXML
    private void aboutWidow(ActionEvent event) {
        if (aboutDialog.isVisible()) {
            return;
        }
        aboutDialog.show();
    }

}
