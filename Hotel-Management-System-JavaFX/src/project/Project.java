package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author UpToDate
 */
public class Project extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // load launcherScene.fxml in layout of type Parent
            Parent root = FXMLLoader.load(getClass()
                    .getResource("launcherScene.fxml"));
            // Create obj of type scene , add layout in it
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            
            Image icon = new Image(getClass()
                    .getResourceAsStream("/img/launcher_icon.png"));
            
            stage.getIcons().add(icon);
            
                
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println("Launcher Scene Error");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
