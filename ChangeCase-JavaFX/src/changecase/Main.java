package changecase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

        Scene scene = new Scene(root);
              scene.getStylesheets().add(
                getClass().getResource("/resources/BaseStructure.css").toExternalForm());
        stage.setScene(scene);
        scene.getStylesheets().add(
                getClass().getResource("/resources/green-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Change Case");
        stage.getIcons().add(new Image(getClass()
                .getResourceAsStream("/images/change-case.png")));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
