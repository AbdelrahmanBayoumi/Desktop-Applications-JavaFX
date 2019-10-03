package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Abdelrahman Bayoumi
 * @GitHub https://github.com/AbdelrahmanBayoumi
 * @Email abdelrahmanbayoumi1@gmail.com
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Main -> start() ...");
        Parent root = FXMLLoader.load(getClass().getResource("/appWindow/App.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/AppIcon.png")));
        stage.setTitle("Convert To Icon");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Main -> main() ...");
        launch(args);
    }

}
