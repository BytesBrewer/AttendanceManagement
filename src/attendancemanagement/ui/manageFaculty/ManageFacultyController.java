/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancemanagement.ui.manageFaculty;

import attendancemanagement.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ManageFacultyController implements Initializable {

    @FXML
    private JFXTextField facultySearch;
    @FXML
    private Label username;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label gender;
    @FXML
    private Label email;
    @FXML
    private Label dob;
    @FXML
    private Label address;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton search;
    @FXML
    private AnchorPane details;
    @FXML
    private AnchorPane noData;

    ResultSet rs;
    DatabaseHandler database = new DatabaseHandler();
     
    @FXML
    private AnchorPane updateDetails;
    @FXML
    private JFXButton save;
    @FXML
    private JFXTextField usernameText;
    private JFXTextField nameText;
    @FXML
    private JFXTextField phoneText;
    @FXML
    private JFXTextField emailText;
    @FXML
    private JFXTextField addressText;
    @FXML
    private JFXComboBox<String> genderText;
    @FXML
    private JFXDatePicker dateText;
    
    ObservableList<String> genderList = FXCollections.observableArrayList("Male","Female");
    @FXML
    private JFXTextField firstNameText;
    @FXML
    private JFXTextField lastNameText;
    @FXML
    private JFXButton deleteData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    

    @FXML
    private void updateData(ActionEvent event) throws IOException, SQLException 
    {
        details.setVisible(false);
        delete.setVisible(false);
        update.setVisible(false);
        usernameText.setText(rs.getString("username"));
        firstNameText.setText(rs.getString("firstName"));
        lastNameText.setText(rs.getString("lastName"));
        phoneText.setText(rs.getString("number"));
        emailText.setText(rs.getString("email"));
        addressText.setText(rs.getString("address"));
        genderText.setValue(rs.getString("gender"));
        genderText.setItems(genderList);
        updateDetails.setVisible(true);
    }

    private void deleteData(ActionEvent event) 
    {
        String qu = "DELETE FROM FACULTY WHERE firstName = '" + facultySearch.getText() + "'";
        if(database.exec(qu))
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Data successfully deleted");
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Data successfully deleted");
        }
    }

    @FXML
    private void searchData(ActionEvent event) throws SQLException 
    {
        if(facultySearch.getLength()>=1)
        {
            String qu = "SELECT * FROM FACULTY WHERE firstName = '" + facultySearch.getText() + "' OR lastName = '" + facultySearch.getText() + "'";
            rs = database.execQuery(qu);
            if(rs.next())
            {
                noData.setVisible(false);
                username.setText(rs.getString("username"));
                name.setText(rs.getString("firstName").concat(" ").concat(rs.getString("lastName")));
                gender.setText(rs.getString("gender"));
                phone.setText(rs.getString("number"));
                email.setText(rs.getString("email"));
                dob.setText(rs.getString("dob"));
                address.setText(rs.getString("address"));
                details.setVisible(true);
                deleteData.setVisible(true);
                update.setVisible(true);
            }
            else
            {
                details.setVisible(false);
                deleteData.setVisible(false);
                update.setVisible(false);
                noData.setVisible(true);
            }            
        }
    }

    @FXML
    private void saveUpdate(ActionEvent event) throws SQLException 
    {
        String qu = "UPDATE FACULTY SET username = '" + usernameText.getText() 
                + "' , firstName ='" + firstNameText.getText() 
                + "' , lastName = '" + lastNameText.getText() 
                + "' , gender ='" + genderText.getValue() 
                + "' , number = '" + phoneText.getText() 
                + "' , email = '" + emailText.getText() 
                + "' , address = '" + addressText.getText() 
                + "', dob = '" + dateText.getValue().toString() 
                + "' WHERE username = '" + rs.getString("username") + "'";
        if(database.exec(qu))
        {
            Alert succ = new Alert(AlertType.INFORMATION);
            succ.setContentText("Data Successfully updated");
            succ.showAndWait();
        }
        else
        {
            Alert succ = new Alert(AlertType.ERROR);
            succ.setContentText("Failed to update data");
            succ.showAndWait();
        }
    }

    @FXML
    private void deleteDataButton(ActionEvent event) {
    }
    
}
