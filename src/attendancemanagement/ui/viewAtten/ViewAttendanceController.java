package attendancemanagement.ui.viewAtten;

import attendancemanagement.database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewAttendanceController implements Initializable 
{
    ObservableList<Attendance> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Attendance> attenTable;
    @FXML
    private TableColumn<Attendance, Integer> rollCol;
    @FXML
    private TableColumn<Attendance, String> dateCol;
    @FXML
    private TableColumn<Attendance, String> dayCol;
    @FXML
    private TableColumn<Attendance, Boolean> slot1Col;
    @FXML
    private TableColumn<Attendance, Boolean> slot2Col;
    @FXML
    private TableColumn<Attendance, Boolean> slot3Col;
    @FXML
    private TableColumn<Attendance, Boolean> slot4Col;
    @FXML
    private TableColumn<Attendance, Boolean> slot5Col;
    @FXML
    private TableColumn<Attendance, Boolean> slot6Col;
    @FXML
    private TableColumn<Attendance, Integer> totalCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initCol();
        loadData();
    }    
    
    private void initCol()
    {
        rollCol.setCellValueFactory(new PropertyValueFactory<>("rollNo"));
        rollCol.setStyle("-fx-alignment: CENTER_RIGHT;");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setStyle("-fx-alignment: CENTER_RIGHT;");
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        dayCol.setStyle("-fx-alignment: CENTER_RIGHT;");
        slot1Col.setCellValueFactory(new PropertyValueFactory<>("slot1"));
        slot1Col.setStyle("-fx-alignment: CENTER_RIGHT;");
        slot2Col.setCellValueFactory(new PropertyValueFactory<>("slot2"));
        slot2Col.setStyle("-fx-alignment: CENTER_RIGHT;");
        slot3Col.setCellValueFactory(new PropertyValueFactory<>("slot3"));
        slot3Col.setStyle("-fx-alignment: CENTER_RIGHT;");
        slot4Col.setCellValueFactory(new PropertyValueFactory<>("slot4"));
        slot4Col.setStyle("-fx-alignment: CENTER_RIGHT;");
        slot5Col.setCellValueFactory(new PropertyValueFactory<>("slot5"));
        slot5Col.setStyle("-fx-alignment: CENTER_RIGHT;");
        slot6Col.setCellValueFactory(new PropertyValueFactory<>("slot6"));
        slot6Col.setStyle("-fx-alignment: CENTER_RIGHT;");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        totalCol.setStyle("-fx-alignment: CENTER_RIGHT;");
    }
    
    private void loadData()
    {
        String slot1x = "",slot2x = "",slot3x = "",slot4x = "",slot5x = "",slot6x = "";
        DatabaseHandler database = new DatabaseHandler();
        String qu = "SELECT * FROM ATTENDANCE";
        ResultSet rs = database.execQuery(qu);
        try
        {
            while(rs.next())
            {
                Integer rollx = rs.getInt("rollNo");
                String datex = rs.getString("date");
                String dayx = rs.getString("day");
                Boolean slot1 = rs.getBoolean("slot1");
                if(slot1==true)
                {
                    slot1x = "P";
                }
                else
                {
                    slot1x = "A";
                }
                Boolean slot2 = rs.getBoolean("slot2");
                if(slot2==true)
                {
                    slot2x = "P";
                }
                else
                {
                    slot2x = "A";
                }
                Boolean slot3 = rs.getBoolean("slot3");
                if(slot3)
                {
                    slot3x = "P";
                }
                else
                {
                    slot3x = "A";
                }
                Boolean slot4 = rs.getBoolean("slot4");
                if(slot4)
                {
                    slot4x = "P";
                }
                else
                {
                    slot4x = "A";
                }
                Boolean slot5 = rs.getBoolean("slot5");
                if(slot5)
                {
                    slot5x = "P";
                }
                else
                {
                    slot5x = "A";
                }
                Boolean slot6 = rs.getBoolean("slot6");
                if(slot6)
                {
                    slot6x = "P";
                }
                else
                {
                    slot6x = "A";
                }
                Integer totalx = rs.getInt("total");
                list.add(new Attendance(rollx , datex , dayx , slot1x , slot2x , slot3x , slot4x , slot5x , slot6x , totalx));
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ViewAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        attenTable.getItems().setAll(list);
    }
    
    public static class Attendance
    {
        private final SimpleIntegerProperty rollNo;
        private final SimpleStringProperty date;
        private final SimpleStringProperty day;
        private final SimpleStringProperty slot1;
        private final SimpleStringProperty slot2;
        private final SimpleStringProperty slot3;
        private final SimpleStringProperty slot4;
        private final SimpleStringProperty slot5;
        private final SimpleStringProperty slot6;
        private final SimpleIntegerProperty total;
        
        public Attendance(Integer rollNo ,String date ,String day ,String slot1 ,String slot2 ,String slot3 , String slot4 , String slot5 , String slot6 , Integer total)
        {
            this.rollNo = new SimpleIntegerProperty(rollNo);
            this.date = new SimpleStringProperty(date);
            this.day = new SimpleStringProperty(day);
            this.slot1 = new SimpleStringProperty(slot1);
            this.slot2 = new SimpleStringProperty(slot2);
            this.slot3 = new SimpleStringProperty(slot3);
            this.slot4 = new SimpleStringProperty(slot4);
            this.slot5 = new SimpleStringProperty(slot5);
            this.slot6 = new SimpleStringProperty(slot6);
            this.total = new SimpleIntegerProperty(total);
        }
        public Integer getRollNo()
        {
            return rollNo.get();
        }
        public String getDate()
        {
            return date.get();
        }
        public String getDay()
        {
            return day.get();
        }
        public String getSlot1()
        {
            return slot1.get();
        }
        public String getSlot2()
        {
            return slot2.get();
        }
        public String getSlot3()
        {
            return slot3.get();
        }
        public String getSlot4()
        {
            return slot4.get();
        }
        public String getSlot5()
        {
            return slot5.get();
        }
        public String getSlot6()
        {
            return slot6.get();
        }
        public Integer getTotal()
        {
            return total.get();
        }
    }
}
