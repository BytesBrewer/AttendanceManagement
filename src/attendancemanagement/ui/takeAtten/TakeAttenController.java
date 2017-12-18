
package attendancemanagement.ui.takeAtten;

import attendancemanagement.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TakeAttenController implements Initializable {
    
    DatabaseHandler database = new DatabaseHandler();

    @FXML
    private JFXButton confirm;
    @FXML
    private JFXButton cancel;
    @FXML
    private JFXComboBox<String> slot;
    
    ObservableList<String> timelist=FXCollections.observableArrayList("8.30 - 9.30",
        "9.30 - 10.30",
        "10.45 - 11.45",
        "11.45 - 12.45",
        "1.15 - 2.15",
        "2.15 - 3.15");
    @FXML
    private JFXTextField rollInput;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        slot.setValue("8.30 - 9.30");
        slot.setItems(timelist);
    }    

    @FXML
    private void confirm(ActionEvent event) throws SQLException 
    {
        int i=0,temp_total = 6;
        String cond = "TRUE";
        long millis = System.currentTimeMillis();
        java.sql.Date temp_date = new java.sql.Date(millis);
        String date = temp_date.toString().toUpperCase();
        
        LocalDate day = LocalDate.now();
        String dow = day.getDayOfWeek().toString();

        String[] rollNum = rollInput.getText().split("\\s*,\\s*");
        int j=0;
        
        String lecture;
        switch (slot.getValue()) {
            case "8.30 - 9.30":
                lecture = "slot1";
                break;
            case "9.30 - 10.30":
                lecture = "slot2";
                break;
            case "10.45 - 11.45":
                lecture = "slot3";
                break;
            case "11.45 - 12.45":
                lecture = "slot4";
                break;
            case "1.15 - 2.15":
                lecture = "slot5";
                break;
            default:
                lecture = "slot6";
                break;
        }
            ResultSet rs = database.execQuery("SELECT * FROM ATTENDANCE WHERE date = '" + date + "'");
            if(!rs.next())
            {
                ResultSet rt = database.execQuery("SELECT roll FROM STUDENT");
                while(rt.next())
                {
                    Integer temp_roll = rt.getInt("roll");
                    for (String rollNum1 : rollNum) {
                        if (temp_roll.toString().equals(rollNum1)) {
                            cond = "FALSE";
                            temp_total = 5;
                        } else {
                            cond = "TRUE";
                            temp_total = 6;
                        }    
                    }
                    database.execUpdate("INSERT INTO ATTENDANCE(rollNo,date,day," + lecture +",total) VALUES("
                                 + rt.getInt(1) + ",'"
                                 + date + "','"
                                 + dow + "',"
                                 + cond + ","
                                 + temp_total + ")");
                }
                System.out.println("Inserted roll no into attendance sheet");
            }
            else
            {
                i=0;
                ResultSet total;
                while(j < rollNum[i].length())
                {
                    total = database.execQuery("SELECT total FROM ATTENDANCE WHERE rollNo=rollNum[i]");
                    total.next();
                    database.execUpdate("UPDATE ATTENDANCE SET " + lecture + "=FALSE total=" + (total.getInt("total")-1) + "WHERE rollNo=" + rollNum[i]);
                    j++;
                    i++;
                }
                System.out.println("Updated roll no into attendance sheet");
            }
    }
    
    @FXML
    private void cancel(ActionEvent event) throws IOException 
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/faculty_Login/FacultyLogin.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
    }
    
}
