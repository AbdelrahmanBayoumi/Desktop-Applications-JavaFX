package appWindow;

import java.awt.Desktop;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import net.sf.image4j.codec.ico.ICOEncoder;

/**
 * @author Abdelrahman Bayoumi
 * @GitHub https://github.com/AbdelrahmanBayoumi
 * @Email abdelrahmanbayoumi1@gmail.com
 */
public class AppController implements Initializable {

    @FXML
    private TextField textField;
    @FXML
    private Label statusLabel;

    FileChooser fil_chooser = new FileChooser();
    File fileSource;
    Parent root_help;
    Stage helpStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("AppController -> initialize() ...");
        try {
            statusLabel.setText("Choose image file.");
            //help window 
            root_help = FXMLLoader.load(getClass().getResource("/Help/Help.fxml"));
            Scene sc = new Scene(root_help);
            helpStage = new Stage();
            helpStage.setScene(sc);
            helpStage.setTitle("Help");
            helpStage.initModality(Modality.APPLICATION_MODAL);
            helpStage.initStyle(StageStyle.UNDECORATED);
        } catch (Exception e) {
            System.out.println("Exception in AppController -> initialize() ..." + e);
        }
    }

    @FXML
    private void openAction(ActionEvent event) {
        System.out.println("AppController -> openAction() ...");

        fileSource = fil_chooser.showOpenDialog(textField.getScene().getWindow());
        if (fileSource != null) {
            textField.setText(fileSource.getAbsolutePath());
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Ready");
        } else {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("No file selected !");
            textField.setText("");
        }
    }

    @FXML
    private void convertAction(ActionEvent event) {
        System.out.println("AppController -> convertAction() ...");
        statusLabel.setTextFill(Color.BLACK);
        statusLabel.setText("Converting ... ");
        try {
            BufferedImage bi = ImageIO.read(fileSource);
            ICOEncoder.write(bi, new File(getNewPath(fileSource.getAbsolutePath())));
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Done : " + getNewName(fileSource.getAbsolutePath()));
            Desktop.getDesktop().open(new File(getFolder(fileSource.getAbsolutePath())));
        } catch (Exception ex) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("No file selected !");
            textField.setText("");
        }
    }

    @FXML
    private void helpAction(MouseEvent event) {
        helpStage.showAndWait();
    }

    private String getNewPath(String old) {
        String NewPath = old.substring(0, old.indexOf('.'))
                + "_Converted.ico";
        return NewPath;
    }

    private String getNewName(String old) {
        String NewPath = old.substring(old.lastIndexOf("\\") + 1)
                + "_Converted.ico";
        return NewPath;
    }

    private String getFolder(String old) {
        String NewPath = old.substring(0, old.lastIndexOf("\\"));
        return NewPath;
    }
}
