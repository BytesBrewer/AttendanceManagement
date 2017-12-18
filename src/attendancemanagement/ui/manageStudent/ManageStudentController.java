package attendancemanagement.ui.manageStudent;

import attendancemanagement.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class ManageStudentController implements Initializable 
{
    DatabaseHandler database = new DatabaseHandler();
    ResultSet rs;

    @FXML
    private JFXTextField studentSearch;
    @FXML
    private JFXButton search;
    @FXML
    private ScrollPane details;
    @FXML
    private Label name;
    @FXML
    private Label father;
    @FXML
    private Label phone;
    @FXML
    private Label gender;
    @FXML
    private Label rollNo;
    @FXML
    private Label grNo;
    @FXML
    private Label branch;
    @FXML
    private Label year;
    @FXML
    private Label dob;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private JFXButton updateData;
    @FXML
    private JFXButton deleteData;
    @FXML
    private Label mother;
    @FXML
    private AnchorPane noData;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
    }    

    @FXML
    private void searchData(ActionEvent event) throws SQLException 
    {
        if(studentSearch.getLength()>=1)
        {
            String qu = "SELECT * FROM STUDENT WHERE firstName = '" + studentSearch.getText() + "' OR lastName = '" + studentSearch.getText() + "'";
            rs = database.execQuery(qu);
            if(rs.next())
            {
                noData.setVisible(false);
                father.setText(rs.getString("father"));
                name.setText(rs.getString("firstName").concat(" ").concat(rs.getString("lastName")));
                gender.setText(rs.getString("gender"));
                phone.setText(rs.getString("number"));
                email.setText(rs.getString("email"));
                dob.setText(rs.getString("dob"));
                mother.setText(rs.getString("mother"));
                address.setText(rs.getString("address"));
                year.setText(rs.getString("class"));
                branch.setText(rs.getString("branch"));
                rollNo.setText(rs.getString("roll"));
                grNo.setText(rs.getString("grno"));
                details.setVisible(true);
                deleteData.setVisible(true);
                updateData.setVisible(true);
            }
            else
            {
                details.setVisible(false);
                deleteData.setVisible(false);
                updateData.setVisible(false);
                noData.setVisible(true);
            }            
        }
    }

    @FXML
    private void updateData(ActionEvent event) {
    }

    @FXML
    private void deleteData(ActionEvent event) {
    }
    
}
