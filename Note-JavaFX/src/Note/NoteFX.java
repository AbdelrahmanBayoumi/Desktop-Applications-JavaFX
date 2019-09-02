package Note;

import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class NoteFX extends Parent {

    String text;
    String hexaColor;
    int id;
//    AnchorPane AP;
//    VBox vBox;

    public NoteFX(int id, String text, String hexaColor) {
        this.id = id;
        this.text = text;
        this.hexaColor = hexaColor;

//        JFXTextArea textArea = new JFXTextArea();
//        textArea.setPromptText("Take a note ...");
//        if (text != null) {
//            textArea.setText(text);
//        }
//        textArea.setPadding(new Insets(5));
//        textArea.setPrefSize(260, 260);
//
//        vBox = new VBox(textArea);
//
//        AP = new AnchorPane(vBox);
//        AP.setPrefSize(260, 260);
//        AP.setStyle("-fx-background-color : " + hexaColor);
    }

    public String getHexaColor() {
        return hexaColor;
    }

    public void setHexaColor(String hexaColor) {
        this.hexaColor = hexaColor;
    }

    public int getid() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "NoteFX{" + "text=" + text + ", hexaColor=" + hexaColor + ", id=" + id + '}';
    }
}
