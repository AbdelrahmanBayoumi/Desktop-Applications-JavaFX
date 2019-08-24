package SimpleCalcFX;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SimlpeClac extends Application {

    boolean ISopUsed = false;
    boolean IsDotUsed = false;
    int minusCounter = 0;
    double data = 0;
    int operation = -1;

    public static int fact(int n) {
        int result;
        if (n == 1) {
            return 1;
        }
        result = fact(n - 1) * n;
        return result;
    }

    public boolean smallText(Label lable) {
        return lable.getText().length() > 22;
    }

    @Override
    public void start(Stage primaryStage) {

        Label displayField = new Label();
        displayField.setPrefSize(280, 70);
        displayField.setMaxSize(370, 400);
        displayField.setFocusTraversable(false);
        //Label displayField = new Label();
        displayField.setBackground((new Background((new BackgroundFill(Color.rgb(255, 255, 255), new CornerRadii(7), Insets.EMPTY)))));
        displayField.setPadding(new Insets(20));
        displayField.setFont(Font.font(25));
        displayField.setAlignment(Pos.CENTER_RIGHT);
        displayField.setMaxSize(380, 400);
        displayField.setFocusTraversable(false);
        Button dot = new Button(".");
        dot.setPrefSize(90, 70);
        dot.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button zero = new Button("0");
        zero.setPrefSize(90, 70);
        /*    zero.setBackground((new Background((new BackgroundFill(Color.rgb(222, 222, 222)
                , new CornerRadii(7), Insets.EMPTY)))));*/
        //zero.setStyle("-fx-font-weight: bold");
        zero.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button one = new Button("1");
        one.setPrefSize(90, 70);
        one.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        /*       one.setBackground((new Background((new BackgroundFill(Color.rgb(222, 222, 222)
                , new CornerRadii(7), Insets.EMPTY)))));*/
        one.setFocusTraversable(true);
        Button two = new Button("2");
        two.setPrefSize(90, 70);
        two.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button three = new Button("3");
        three.setPrefSize(90, 70);
        three.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button four = new Button("4");
        four.setPrefSize(90, 70);
        four.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button five = new Button("5");
        five.setPrefSize(90, 70);
        five.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button six = new Button("6");
        six.setPrefSize(90, 70);
        six.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button seven = new Button("7");
        seven.setPrefSize(90, 70);
        seven.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button eight = new Button("8");
        eight.setPrefSize(90, 70);
        eight.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button nine = new Button("9");
        nine.setPrefSize(90, 70);
        nine.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        Button plus = new Button("+");
        plus.setPrefSize(90, 70);
        plus.setFont(Font.font(30));
        Button sub = new Button("−");
        sub.setPrefSize(90, 70);
        sub.setFont(Font.font(30));
        Button mult = new Button("×");
        mult.setPrefSize(90, 70);
        mult.setFont(Font.font(30));
        Button div = new Button("÷");
        div.setPrefSize(90, 70);
        div.setFont(Font.font(30));
        Button equal = new Button("=");
        equal.setPrefSize(90, 70);
        equal.setFont(Font.font(30));
        Button clear = new Button("C");
        clear.setPrefSize(90, 70);
        clear.setFont(Font.font(30));
        Button sqrt = new Button("√");
        sqrt.setPrefSize(90, 70);
        sqrt.setFont(Font.font(30));
        Button square = new Button("χ2");
        square.setPrefSize(90, 70);
        square.setFont(Font.font(22));
        Button one_OVER_num = new Button("1/x");
        one_OVER_num.setPrefSize(90, 70);
        one_OVER_num.setFont(Font.font(22));
        Button ChaneSign = new Button("±");
        ChaneSign.setPrefSize(90, 70);
        ChaneSign.setFont(Font.font(30));
        Button factorialbtn = new Button("n!");
        factorialbtn.setPrefSize(90, 70);
        factorialbtn.setFont(Font.font(30));
        Button percentagebtn = new Button("%");
        percentagebtn.setPrefSize(90, 70);
        percentagebtn.setFont(Font.font(30));
        Button deleteButton = new Button("del");
        deleteButton.setPrefSize(90, 70);
        deleteButton.setFont(Font.font(30));
        Button modButton = new Button("mod");
        modButton.setPrefSize(90, 70);
        modButton.setFont(Font.font(25));
        dot.setOnAction((ActionEvent event) -> {
            if ("".equals(displayField.getText())) {
                displayField.setText(displayField.getText() + "0.");
                IsDotUsed = true;
                ISopUsed = false;
                System.out.println("emtpy dis");
            } else if (!displayField.getText().contains(".")) {
                displayField.setText(displayField.getText() + ".");
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
                displayField.setText(displayField.getText() + "0.");
                IsDotUsed = true;
                System.out.println("555");
            }

        });
        zero.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "0");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "0");
        });
        one.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "1");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "1");
        });
        two.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "2");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "2");
        });
        three.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "3");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "3");
        });
        four.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "4");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "4");
        });
        five.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "5");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "5");
        });
        six.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "6");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "6");
        });
        seven.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "7");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "7");
        });
        eight.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "8");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "8");
        });
        nine.setOnAction((ActionEvent event) -> {
            if (smallText(displayField)) {
                if (ISopUsed) {
                    displayField.setText("");
                    ISopUsed = false;
                    displayField.setText(displayField.getText() + "9");
                }
                return;
            }
            if (ISopUsed) {
                displayField.setText("");
                ISopUsed = false;
            }
            displayField.setText(displayField.getText() + "9");
        });
        clear.setOnAction((ActionEvent event) -> {
            displayField.setText("");
            data = 0;
        });
        plus.setOnAction((ActionEvent event) -> {
            data = Double.parseDouble(displayField.getText());
            operation = 1; // addition
            displayField.setText("");
        });
        sub.setOnAction((ActionEvent event) -> {
            data = Double.parseDouble(displayField.getText());
            operation = 2; // sub
            displayField.setText("");

        });
        mult.setOnAction((ActionEvent event) -> {
            data = Double.parseDouble(displayField.getText());
            operation = 3; // mult
            displayField.setText("");
        });
        div.setOnAction((ActionEvent event) -> {
            data = Double.parseDouble(displayField.getText());
            operation = 4; // div
            displayField.setText("");
        });
        sqrt.setOnAction((ActionEvent event) -> {
            if (Double.parseDouble(displayField.getText()) > 0) {
                data = Math.sqrt(Double.parseDouble(displayField.getText()));
                displayField.setText(data + "");
            } else {
                data = Math.sqrt(-1 * Double.parseDouble(displayField.getText()));
                displayField.setText(-1 * data + " i");
            }
            ISopUsed = true;
        });
        square.setOnAction((ActionEvent event) -> {
            data = Math.pow(Double.parseDouble(displayField.getText()), 2);
            displayField.setText(data + "");
            ISopUsed = true;
        });
        one_OVER_num.setOnAction((ActionEvent event) -> {
            if ((Double.parseDouble(displayField.getText())) != 0) {
                data = 1 / (Double.parseDouble(displayField.getText()));
                displayField.setText(data + "");
            } else {
                displayField.setText("Cannot divide by 0 !");
            }
            ISopUsed = true;
        });
        ChaneSign.setOnAction((ActionEvent event) -> {
            data = -1 * (Double.parseDouble(displayField.getText()));
            displayField.setText(data + "");
            ISopUsed = true;
        });
        deleteButton.setOnAction((ActionEvent event) -> {
            if (!"".equals(displayField.getText())) {
                StringBuffer sb = new StringBuffer(displayField.getText());
                sb = sb.deleteCharAt(displayField.getText().length() - 1);
                displayField.setText(sb.toString());
            }
        });
        modButton.setOnAction((ActionEvent event) -> {
            data = Double.parseDouble(displayField.getText());
            operation = 5; // mod
            displayField.setText("");
        });
        factorialbtn.setOnAction((ActionEvent event) -> {
            data = fact((int) Double.parseDouble(displayField.getText()));
            displayField.setText(data + "");
            ISopUsed = true;
        });

        equal.setOnAction((ActionEvent event) -> {
            if (!ISopUsed) {
                if (!"Error".equals(displayField.getText())) {
                    ISopUsed = true;
                    double secondOprand = Double.parseDouble(displayField.getText());
                    double res;
                    switch (operation) {
                        case 1://add
                            res = data + secondOprand;
                            displayField.setText(String.valueOf(res));
                            break;
                        case 2://sub

                            res = data - Math.pow(-1, minusCounter) * (secondOprand);
                            displayField.setText(String.valueOf(res));
                            break;
                        case 3://mult
                            res = data * secondOprand;
                            displayField.setText(String.valueOf(res));
                            break;
                        case 4://div
                            if (secondOprand == 0) {
                                displayField.setText("Error");
                            } else {
                                res = data / secondOprand;
                                displayField.setText(String.valueOf(res));
                            }
                            break;
                        case 5://mod
                            res = data % secondOprand;
                            displayField.setText(String.valueOf(res));
                            break;
                    }
                }
            }

        });

        VBox vbox = new VBox();
//        vbox.setSpacing(0);
        vbox.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 5, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.add(clear, 0, 0);
        gridPane.add(modButton, 1, 0);
        gridPane.add(deleteButton, 2, 0);
        gridPane.add(div, 3, 0);
        gridPane.add(seven, 0, 1);
        gridPane.add(eight, 1, 1);
        gridPane.add(nine, 2, 1);
        gridPane.add(mult, 3, 1);
        gridPane.add(four, 0, 2);
        gridPane.add(five, 1, 2);
        gridPane.add(six, 2, 2);
        gridPane.add(sub, 3, 2);
        gridPane.add(one, 0, 3);
        gridPane.add(two, 1, 3);
        gridPane.add(three, 2, 3);
        gridPane.add(plus, 3, 3);
        gridPane.add(ChaneSign, 0, 4);
        gridPane.add(zero, 1, 4);
        gridPane.add(dot, 2, 4);
        gridPane.add(equal, 3, 4);
        GridPane gridPane2 = new GridPane();
        gridPane2.setAlignment(Pos.CENTER);
        gridPane2.setPadding(new Insets(0, 10, 10, 10));
        gridPane2.setVgap(5);
        gridPane2.setHgap(5);
        gridPane2.add(factorialbtn, 0, 0);
        gridPane2.add(sqrt, 1, 0);
        gridPane2.add(square, 2, 0);
        gridPane2.add(one_OVER_num, 3, 0);
        //Setting the margin to the nodes 
        VBox.setMargin(displayField, new Insets(10, 10, 0, 10));

        //retrieving the observable list of the VBox 
        ObservableList list = vbox.getChildren();

        //Adding all the nodes to the observable list 
        MenuBar menuBar = new MenuBar();

        // --- Menu File
        Menu menuFile = new Menu("File");

        MenuItem about = new MenuItem("About");
        about.setAccelerator(KeyCombination.keyCombination("i"));
        about.setOnAction((ActionEvent t) -> {
            new About().show();
        });

        MenuItem exit = new MenuItem("Exit");
        exit.setAccelerator(KeyCombination.keyCombination("ctrl + W"));
        exit.setOnAction((ActionEvent t) -> {
            System.exit(0);
        });
        menuFile.getItems().addAll(about, exit);
        menuBar.getMenus().addAll(menuFile);
        Scene scene = new Scene(vbox);
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);

        list.addAll(displayField, gridPane, gridPane2);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                KeyCode keyCode = ke.getCode();
                if (keyCode.equals(KeyCode.ESCAPE)) {
                    displayField.setText("");
                    data = 0;
                }
                if (keyCode.equals(KeyCode.Q)) {
                    System.exit(0);
                }
                if (keyCode.equals(KeyCode.BACK_SPACE)) {
                    if (!"".equals(displayField.getText())) {
                        StringBuffer sb = new StringBuffer(displayField.getText());
                        sb = sb.deleteCharAt(displayField.getText().length() - 1);
                        displayField.setText(sb.toString());
                    }
                }
                if (keyCode.equals(KeyCode.NUMPAD9) || keyCode.equals(KeyCode.DIGIT9)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "9");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "9");

                }
                if (keyCode.equals(KeyCode.NUMPAD8) || keyCode.equals(KeyCode.DIGIT8)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "8");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "8");
                }
                if (keyCode.equals(KeyCode.NUMPAD7) || keyCode.equals(KeyCode.DIGIT7)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "8");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "7");
                }
                if (keyCode.equals(KeyCode.NUMPAD6) || keyCode.equals(KeyCode.DIGIT6)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "8");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "6");
                }
                if (keyCode.equals(KeyCode.NUMPAD5) || keyCode.equals(KeyCode.DIGIT5)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "8");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "5");
                }
                if (keyCode.equals(KeyCode.NUMPAD4) || keyCode.equals(KeyCode.DIGIT4)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "8");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "4");
                }
                if (keyCode.equals(KeyCode.NUMPAD3) || keyCode.equals(KeyCode.DIGIT3)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "8");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "3");
                }
                if (keyCode.equals(KeyCode.NUMPAD2) || keyCode.equals(KeyCode.DIGIT2)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "8");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "2");
                }
                if (keyCode.equals(KeyCode.NUMPAD1) || keyCode.equals(KeyCode.DIGIT1)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "8");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "1");
                }
                if (keyCode.equals(KeyCode.NUMPAD0)) {
                    if (smallText(displayField)) {
                        if (ISopUsed) {
                            displayField.setText("");
                            ISopUsed = false;
                            displayField.setText(displayField.getText() + "8");
                        }
                        return;
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                    }
                    displayField.setText(displayField.getText() + "0");
                }
                if (keyCode.equals(KeyCode.ADD)) {
                    data = Double.parseDouble(displayField.getText());
                    operation = 1; // addition
                    displayField.setText("");
                }
                if (keyCode.equals(keyCode.MULTIPLY)) {
                    data = Double.parseDouble(displayField.getText());
                    operation = 3; // mult
                    displayField.setText("");
                }
                if (keyCode.equals(keyCode.DIVIDE)) {
                    data = Double.parseDouble(displayField.getText());
                    operation = 4; // div
                    displayField.setText("");
                }
                if (keyCode.equals(keyCode.DECIMAL)) {
                    if ("".equals(displayField.getText())) {
                        displayField.setText(displayField.getText() + "0.");
                        IsDotUsed = true;
                        ISopUsed = false;
                        System.out.println("emtpy dis");
                    } else if (!displayField.getText().contains(".")) {
                        displayField.setText(displayField.getText() + ".");
                    }
                    if (ISopUsed) {
                        displayField.setText("");
                        ISopUsed = false;
                        displayField.setText(displayField.getText() + "0.");
                        IsDotUsed = true;
                        System.out.println("555");
                    }

                }
                if (keyCode.equals(keyCode.SUBTRACT)) {
                    data = Double.parseDouble(displayField.getText());
                    operation = 2; // sub
                    displayField.setText("");
                }
                if (keyCode.equals(keyCode.ENTER)) {
                    if (!ISopUsed) {
                        if (!"Error".equals(displayField.getText())) {
                            ISopUsed = true;
                            double secondOprand = Double.parseDouble(displayField.getText());
                            double res;
                            switch (operation) {
                                case 1://add
                                    res = data + secondOprand;
                                    displayField.setText(String.valueOf(res));
                                    break;
                                case 2://sub

                                    res = data - Math.pow(-1, minusCounter) * (secondOprand);
                                    displayField.setText(String.valueOf(res));
                                    break;
                                case 3://mult
                                    res = data * secondOprand;
                                    displayField.setText(String.valueOf(res));
                                    break;
                                case 4://div
                                    if (secondOprand == 0) {
                                        displayField.setText("Error");
                                    } else {
                                        res = data / secondOprand;
                                        displayField.setText(String.valueOf(res));
                                    }
                                    break;
                                case 5://mod
                                    res = data % secondOprand;
                                    displayField.setText(String.valueOf(res));
                                    break;
                            }
                        }
                    }
                }
            }
        }
        );
        primaryStage.setTitle("Calculator");
        Image icon = new Image(getClass().getResourceAsStream("atom.png"));

        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(410);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(410);
        //    primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
