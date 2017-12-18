package attendancemanagement.ui.addStudent;

import attendancemanagement.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
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

public class AddStudentController implements Initializable {

    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField father;
    @FXML
    private JFXTextField mother;
    @FXML
    private JFXTextField grNo;
    @FXML
    private JFXTextField rollNo;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXComboBox<String> gender;
    @FXML
    private JFXComboBox<String> branch;
    @FXML
    private JFXComboBox<String> year;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton cancel;
    
    ObservableList<String> genderList = FXCollections.observableArrayList("Male","Female");
    ObservableList<String> branchList = FXCollections.observableArrayList("Computer","E & TC","Civil","Mechanical");
    ObservableList<String> yearList = FXCollections.observableArrayList("FE","SE","TE","BE");
     
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        gender.setValue("----");
        gender.setItems(genderList);
        branch.setValue("----");
        branch.setItems(branchList);
        year.setValue("----");
        year.setItems(yearList);
    }    

    @FXML
    private void saveData(ActionEvent event)
    {
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || father.getText().isEmpty() || mother.getText().isEmpty() || grNo.getText().isEmpty() || rollNo.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty() || address.getText().isEmpty())
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Incomplete Fields");
            alert.showAndWait();
        }
        else
        {
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setContentText("Are you sure you want to save ? ");
            Optional<ButtonType> result = confirm.showAndWait();
            if(result.get() == ButtonType.OK)
            {
                DatabaseHandler database = new DatabaseHandler();
                if(database.exec("INSERT INTO STUDENT VALUES( '" + grNo.getText() + "', " 
                                                                  + rollNo.getText() + ", '"
                                                                  + firstName.getText() + "', '"
                                                                  + lastName.getText() + "', '"
                                                                  + father.getText() + "', '"
                                                                  + mother.getText() + "', '"
                                                                  + phone.getText() + "', '"
                                                                  + email.getText() + "', '"
                                                                  + gender.getValue() + "', '"
                                                                  + address.getText() + "', '"
                                                                  + year.getValue() + "', '"
                                                                  + branch.getValue() + "', '"
                                                                  + dob.getValue().toString() +"'"
                                                                  + ")"))
                {
                    Alert dataAdded = new Alert(AlertType.INFORMATION);
                    dataAdded.setContentText("Student added to database!");
                    dataAdded.showAndWait();
                    grNo.clear();
                    rollNo.clear();
                    firstName.clear();
                    lastName.clear();
                    father.clear();
                    mother.clear();
                    phone.clear();
                    email.clear();
                    gender.setValue("----");
                    address.clear();
                    year.setValue("----");
                    branch.setValue("----");
                    dob.getEditor().clear();
                    dob.setValue(null);
                }
                else
                {
                    Alert dataAdded = new Alert(AlertType.ERROR);
                    dataAdded.setContentText("Failed to add Data");
                    dataAdded.showAndWait();
                }
            } 
        }
    }

    @FXML
    private void Cancel(ActionEvent event) 
    {
        
    }
    
}
