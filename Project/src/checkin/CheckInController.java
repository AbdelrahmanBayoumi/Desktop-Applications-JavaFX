package checkin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import hotel.Guest;
import hotel.Reservation;
import hotel.Room;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static login.LoginController.decorator;
import static login.LoginController.window;
import project.Paths;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class CheckInController implements Initializable {

    @FXML
    public Label usernameLabel;
    @FXML
    public Hyperlink logoutLink;
    @FXML
    public JFXButton Logobtn;
    @FXML
    public Label backLabel;
    @FXML
    public JFXTextField NameField;
    @FXML
    public JFXTextField PhoneField;
    @FXML
    public JFXTextField EmailField;
    @FXML
    public JFXTextField CityField;
    @FXML
    public JFXTextField NationalityField;
    @FXML
    public JFXTextField PassportField;
    @FXML
    public JFXTextField AddressField;
    @FXML
    public JFXTextField CardNumField;
    @FXML
    public JFXTextField CVCcodeField;
    @FXML
    public JFXButton submitButton;
    @FXML
    public JFXButton clearButton;
    @FXML
    public RadioButton economyType;
    @FXML
    public RadioButton normalType;
    @FXML
    public RadioButton vipType;
    @FXML
    public RadioButton singleCapacity;
    @FXML
    public RadioButton doubleCapacity;
    @FXML
    public RadioButton tripleCapacity;
    @FXML
    public JFXDatePicker CheckInDatePicker;
    @FXML
    public JFXDatePicker CheckoutDatePicker;
    @FXML
    public JFXButton searchRoomButtton;
    @FXML
    public JFXTextField roomIDField;
    @FXML
    public ToggleGroup roomtype;
    @FXML
    public ToggleGroup roomCapacity;

    public static Reservation reservation;

    @FXML
    private JFXButton roomBTN;
    @FXML
    private JFXButton guestsBTN;
    @FXML
    private Label name_error;
    @FXML
    private Label phone_error;
    @FXML
    private Label email_error;
    @FXML
    private Label address_error;
    @FXML
    private Label city_error;
    @FXML
    private Label nationality_error;
    @FXML
    private Label passport_error;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(login.LoginController.user.getUsername());
        CheckInDatePicker.setValue(LocalDate.now());
        // ========= Validators =========
        NumberValidator numberValidator = new NumberValidator("This field must be numbers only.");
//
        CVCcodeField.getValidators().add(numberValidator);
        CardNumField.getValidators().add(numberValidator);
        CVCcodeField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    CVCcodeField.validate();
                }
            }
        });
        CardNumField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    CardNumField.validate();
                }
            }
        });

    }

    //======== input error ========
    static boolean Handle_NameField = false;

    @FXML
    private void Handle_NameField(Event event) {
        NameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if ((NameField.getText()).matches("[A-Za-z\\s]{2,}")) {
                        name_error.setText("valid");
                        name_error.setTextFill(Color.GREEN);
                    } else {
                        name_error.setText("Name must contain only letters");
                        name_error.setTextFill(Color.RED);
                        Handle_NameField = true;
                    }
                }
            }
        });
        if (Handle_NameField) {
            if ((NameField.getText()).matches("[A-Za-z\\s]{2,}")) {
                name_error.setText("valid");
                name_error.setTextFill(Color.GREEN);
            } else {
                name_error.setText("Name must contain only letters");
                name_error.setTextFill(Color.RED);
                Handle_NameField = true;
            }
        }

    }
    static boolean Handle_PhoneField = false;

    @FXML
    private void Handle_PhoneField(Event event) {
        PhoneField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if ((PhoneField.getText()).matches("[\\d\\s]+")) {
                        phone_error.setText("valid");
                        phone_error.setTextFill(Color.GREEN);
                    } else {
                        phone_error.setText("Phone number must be digits only");
                        phone_error.setTextFill(Color.RED);
                        Handle_PhoneField = true;
                    }
                }
            }
        });
        if (Handle_PhoneField) {
            if ((PhoneField.getText()).matches("[\\d\\s]+")) {
                phone_error.setText("valid");
                phone_error.setTextFill(Color.GREEN);
            } else {
                phone_error.setText("Phone number must be digits only");
                phone_error.setTextFill(Color.RED);
            }
        }
    }
    static boolean Handle_EmailField = false;

    @FXML
    private void Handle_EmailField(Event event) {
        EmailField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    if ((EmailField.getText()).matches(EMAIL_REGEX)) {
                        email_error.setText("valid");
                        email_error.setTextFill(Color.GREEN);
                    } else {
                        email_error.setText("Must be at this form : user@domain.com");
                        email_error.setTextFill(Color.RED);
                        Handle_EmailField = true;
                    }
                }
            }
        });
        if (Handle_EmailField) {
            String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if ((EmailField.getText()).matches(EMAIL_REGEX)) {
                email_error.setText("valid");
                email_error.setTextFill(Color.GREEN);
            } else {
                email_error.setText("Must be at this form : user@domain.com");
                email_error.setTextFill(Color.RED);
                Handle_EmailField = true;
            }
        }
    }

    @FXML
    private void logout(Event event) {
        new homepage.HomePageController().logout(event);
    }

    @FXML
    public void goToHomePage(Event event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Paths.HOMEPAGEVIEW));

            decorator.setContent(root);
            decorator.setCustomMaximize(true);
            decorator.setBorder(Border.EMPTY);

            decorator.setTitle("Hotel Reservation System");

            Image icon = new Image(getClass().getResourceAsStream("/img/icon.jpg"));
            window.getIcons().add(icon);
            window.setTitle("Hotel System");
            root.requestFocus();
        } catch (IOException ex) {
            System.out.println("Error load loginFXML !");
        } catch (Exception ex) {
            System.out.println("Error !!");
            ex.printStackTrace();
        }
    }

    private void goToConfirmnData(Event event) {
        try {
            //Load new FXML and assign it to scene
            Parent root = FXMLLoader.load(getClass().getResource("/checkin/ConfirmnData.fxml"));
            //create empty new stage
            Stage window = new Stage();
            //set layout properties
            JFXDecorator decorator = new JFXDecorator(window, root, false, false, true);
            //Create new scene and add new layout in it
            Scene scene = new Scene(decorator);
            // set Title to the new decorator
            decorator.setTitle("Confirmation Data");
            //set the stage properties
            String uri = getClass().getResource("dectaorStyle.css").toExternalForm();
            scene.getStylesheets().add(uri);
            window.setScene(scene);
            window.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.jpg")));
            window.setTitle("Confirmation Data");
            //show homePage stage
            window.show();
            //set foucus in the window not in close and maximize button
            root.requestFocus();

        } catch (IOException ex) {
            System.out.println("Error load ConfirmationData FXML !");
            System.out.println(ex);

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @FXML
    private void goTO_check_in(Event event) {
        new homepage.HomePageController().goTO_check_in(event);
    }

    @FXML
    private void goTo_room_booking(Event event) {
        new homepage.HomePageController().goTo_room_booking(event);
    }

    @FXML
    private void goTO_check_out(Event event) {
        new homepage.HomePageController().goTo_Check_out(event);
    }

    @FXML
    private void goTo_cancel_booking(Event event) {
        new homepage.HomePageController().goTo_cancel_booking(event);
    }

    public String getRoomTypeValue() {
        String RoomType = null;
        if (economyType.isSelected()) {
            RoomType = "Economy";
        } else if (normalType.isSelected()) {
            RoomType = "Normal";
        } else if (vipType.isSelected()) {
            RoomType = "Vip";
        }
        return RoomType;
    }

    public String getRoomCapacityValue() {
        String RoomCapacity = null;
        if (singleCapacity.isSelected()) {
            RoomCapacity = "Single";
        } else if (doubleCapacity.isSelected()) {
            RoomCapacity = "Double";
        } else if (tripleCapacity.isSelected()) {
            RoomCapacity = "Triple";
        }
        return RoomCapacity;
    }

    @FXML
    private void submitAction(Event event) {
        System.out.println("submitAction clicked");

        try {

            if (!isFieldsEmpty()) {
                if (getDifferenceDays(LocalDate_TO_Date(CheckInDatePicker.getValue()),
                        LocalDate_TO_Date(CheckoutDatePicker.getValue())) < 0) {
                    System.out.println("Date diff -ve !!");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
                    alert.setContentText("not valid value for Check-in or Check-out Date \n Try Again !");
                    alert.setHeaderText("Choose Correct Date !");
                    alert.setTitle("Error");
                    ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                    alert.showAndWait();
                    return;
                }
                System.out.println("inside if in try submitAction() ");
                Room room = new Room(getRoomTypeValue(), getRoomCapacityValue(),
                        LocalDate_TO_Date(CheckInDatePicker.getValue()),
                        LocalDate_TO_Date(CheckoutDatePicker.getValue()), false);

                Guest guest = new Guest(Integer.parseInt(roomIDField.getText()),
                        getDifferenceDays(LocalDate_TO_Date(CheckInDatePicker.getValue()),
                                LocalDate_TO_Date(CheckoutDatePicker.getValue())),
                        NameField.getText(), EmailField.getText(),
                        AddressField.getText(), CityField.getText(), NationalityField.getText(),
                        PassportField.getText(), PhoneField.getText(), CardNumField.getText(),
                        CVCcodeField.getText(), 0);
                guest.setFees(guest.CustomerRoomFees(room));
                reservation = new Reservation(room, guest);
                goToConfirmnData(event);
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
                alert.setContentText("you must Fill all fields to go to Confirmation window !");
                alert.setHeaderText("Fill all fields !");
                alert.setTitle("Error");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                alert.showAndWait();

            }

        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all fields !");
            alert.setHeaderText("Fill all fields !");
            alert.setTitle("Error !");
            alert.showAndWait();
        }

    }

    public boolean isFieldsEmpty() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        if ("".equals(NameField.getText()) || "".equals(PhoneField.getText())
                || ("".equals(EmailField.getText())) || ("".equals(AddressField.getText()))
                || ("".equals(CityField.getText())) || ("".equals(NationalityField.getText()))
                || ("".equals(PassportField.getText())) || ("".equals(CardNumField.getText()))
                || ("".equals(CVCcodeField.getText())) || ("".equals(roomIDField.getText()))) {
            System.out.println("Inside if in isFieldsEmpty() ");

            return true;
        } else {

            System.out.println("Inside else in isFieldsEmpty() ");
            return false;
        }
    }

    @FXML
    private void searchRoom(Event event) {
        try {
            if (!isFieldsEmpty()) {
                if (getDifferenceDays(LocalDate_TO_Date(CheckInDatePicker.getValue()),
                        LocalDate_TO_Date(CheckoutDatePicker.getValue())) < 0) {
                    System.out.println("Date diff -ve !!");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
                    alert.setContentText("not valid value for Check-in or Check-out Date \n Try Again !");
                    alert.setHeaderText("Choose Correct Date !");
                    alert.setTitle("Error");
                    ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/WARNING_PNG.png"));
                    alert.showAndWait();
                    return;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill all fields !");
                    alert.setHeaderText("Fill all fields !");
                    alert.setTitle("Error !");
                    alert.showAndWait();
                }

            } else if (getDifferenceDays(LocalDate_TO_Date(CheckInDatePicker.getValue()),
                    LocalDate_TO_Date(CheckoutDatePicker.getValue())) < 0) {
                System.out.println("Date diff -ve !!");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
                alert.setContentText("not valid value for Check-in or Check-out Date \n Try Again !");
                alert.setHeaderText("Choose Correct Date !");
                alert.setTitle("Error");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                alert.showAndWait();
                return;
            } else {
                Room room = Room.Search_available_rooms(getRoomTypeValue(), getRoomCapacityValue());
                if (room == null) {
                    roomIDField.setText("no match !");
                } else {
                    roomIDField.setText(room.getRoomID() + "");
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all fields !");
            alert.setHeaderText("Fill all fields !");
            alert.setTitle("Error !");
            alert.showAndWait();
        }
    }

    @FXML
    private void clearButtonAction(ActionEvent event
    ) {
        System.out.println("Clear button clicked ...");
        new homepage.HomePageController().goTO_check_in(event);
    }

    public boolean comp(Date a, Date b, Date d) {
        return a.compareTo(d) * d.compareTo(b) >= 0;
    }

    public Date LocalDate_TO_Date(LocalDate ld) {
        Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date res = Date.from(instant);
        return res;
    }

    public static int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static LocalDate Date_TO_LocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDate res = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return res;
    }

    @FXML
    private void goto_Rooms(ActionEvent event) {
        new homepage.HomePageController().goTo_Rooms(event);
    }

    @FXML
    private void goTo_guests(ActionEvent event) {
        new homepage.HomePageController().goTo_Guests(event);
    }
}
