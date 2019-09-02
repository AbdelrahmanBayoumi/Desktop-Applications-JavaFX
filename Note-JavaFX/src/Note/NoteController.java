/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Note;

import app.HomeController;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class NoteController implements Initializable {

    public static JFXTextArea textStatic;
    @FXML
    private JFXTextArea text;
    @FXML
    private Label id;
    public static Label idStaitc;

    static Connection con = null;
    @FXML
    private AnchorPane AP;

    public static AnchorPane APStatic;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("NoteController -> initialize() ...");
        textStatic = text;
        idStaitc = id;
        APStatic = AP;
    }

    @FXML
    private void editAction(KeyEvent event) {

        System.out.println("NoteController -> editAction() ...");
        String sqlString = "Update notes set text = ? where id = ?";
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:db/NoteDatabase", "SA", "");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sqlString);
            prepareStatement.setInt(2, Integer.parseInt(id.getText()));
            prepareStatement.setString(1, text.getText());
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    @FXML
    private void delete(ActionEvent event) {
        System.out.println("NoteController -> delete() ...");
        String sqlString = "DELETE FROM notes WHERE id =  ?";
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:db/NoteDatabase", "SA", "");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sqlString);
            prepareStatement.setInt(1, Integer.parseInt(id.getText()));
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("failed to delete table");
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/Home.fxml"));
            id.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }

//        new HomeController().refresh(event);
    }

}
