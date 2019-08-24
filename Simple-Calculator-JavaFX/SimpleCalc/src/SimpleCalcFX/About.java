package SimpleCalcFX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class About {
    public void show(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(350);
        window.setMinHeight(250);
        window.setTitle("  About");
        Image icon = new Image(getClass().getResourceAsStream("info.png"));
        window.getIcons().add(icon);
        Label text = new Label("Calculator 0.0.1\n" + "© 2019 Bayoumi. All rights reserved.\n"+
                "Email : abdelrahmanbayoumi1@gmail.com.");
        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> window.close());
        VBox vbox = new VBox(text,closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
        
    }
}
