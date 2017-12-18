package attendancemanagement.ui.addFaculty;

import attendancemanagement.database.DatabaseHandler;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class AddFacultyController implements Initializable {

    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField cPassword;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXComboBox gender;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXDatePicker dob;
    
    @FXML
    private ScrollPane ScrollPane;
    
    ObservableList<String> genderList = FXCollections.observableArrayList("Male","Female");
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        gender.setValue("Gender");
        gender.setItems(genderList);
    }    

    @FXML
    private void saveData(ActionEvent event)
    {
        if(username.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty() || address.getText().isEmpty() || password.getText().isEmpty() || cPassword.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Incomplete Fields");
            alert.showAndWait();
        }
        else if(!password.getText().equals(cPassword.getText()))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("password does not match");
        }
        else
        {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Are you sure you want to save ? ");
            Optional<ButtonType> result = confirm.showAndWait();
            if(result.get() == ButtonType.OK)
            {
                DatabaseHandler database = new DatabaseHandler();
                if(database.exec("INSERT INTO FACULTY VALUES( '" + username.getText() + "', '"
                                                                  + password.getText() + "', '"
                                                                  + firstName.getText() + "', '"
                                                                  + lastName.getText() + "', '"
                                                                  + phone.getText() + "', '"
                                                                  + email.getText() + "', '"
                                                                  + gender.getValue() + "', '"
                                                                  + address.getText() + "', '"
                                                                  + dob.getValue().toString() +"', ' null'"
                                                                  + ")"))
                {
                    Alert dataAdded = new Alert(Alert.AlertType.INFORMATION);
                    dataAdded.setContentText("Faculty added to database!");
                    dataAdded.showAndWait();
                    username.clear();
                    password.clear();
                    cPassword.clear();
                    firstName.clear();
                    lastName.clear();
                    phone.clear();
                    email.clear();
                    gender.setValue("Gender");
                    address.clear();
                    dob.getEditor().clear();
                    dob.setValue(null);
                }
                else
                {
                    Alert dataAdded = new Alert(Alert.AlertType.ERROR);
                    dataAdded.setContentText("Failed to add Data");
                    dataAdded.showAndWait();
                }
            } 
        }
    }
}
