package attendancemanagement.ui.viewFaculty;

import attendancemanagement.database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewFacultyController implements Initializable {
    
    ObservableList<Faculty> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Faculty> FacultyTable;
    @FXML
    private TableColumn<Faculty, String> nameCol;
    @FXML
    private TableColumn<Faculty, String> usernameCol;
    @FXML
    private TableColumn<Faculty, String> phoneCol;
    @FXML
    private TableColumn<Faculty, String> dobCol;
    @FXML
    private TableColumn<Faculty, String> genderCol;
    @FXML
    private TableColumn<Faculty, String> addressCol;
    @FXML
    private TableColumn<Faculty, String> emailCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initCol();
        loadData();
    }    
    private void initCol()
    {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    private void loadData()
    {
        DatabaseHandler database = new DatabaseHandler();
        String qu = "SELECT * FROM FACULTY";
        ResultSet rs = database.execQuery(qu);
        try
        {
            while(rs.next())
            {
                String namex = rs.getString("firstName");
                namex = namex.concat(" ");
                namex = namex.concat(rs.getString("lastName"));
                String usernamex = rs.getString("username");
                String genderx = rs.getString("gender");
                String emailx = rs.getString("email");
                String phonex = rs.getString("number");
                String dobx = rs.getString("dob");
                String addressx = rs.getString("address");
                
                list.add(new Faculty(namex , usernamex , emailx , phonex , dobx , genderx , addressx));
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ViewFacultyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacultyTable.getItems().setAll(list);
    }
    public static class Faculty
    {
        private final SimpleStringProperty name;
        private final SimpleStringProperty username;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty email;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty dob;
        private final SimpleStringProperty address;
        
        public Faculty(String name ,String username ,String email ,String phone ,String dob ,String gender , String address)
        {
            this.name = new SimpleStringProperty(name);
            this.username = new SimpleStringProperty(username);
            this.gender = new SimpleStringProperty(gender);
            this.email = new SimpleStringProperty(email);
            this.phone = new SimpleStringProperty(phone);
            this.dob = new SimpleStringProperty(dob);
            this.address = new SimpleStringProperty(address);
            
        }
        public String getName()
        {
            return name.get();
        }
        public String getGender()
        {
            return gender.get();
        }
        public String getEmail()
        {
            return email.get();
        }
        public String getDob()
        {
            return dob.get();
        }
        public String getPhone()
        {
            return phone.get();
        }
        public String getAddress()
        {
            return address.get();
        }
        public String getUsername()
        {
            return username.get();
        }
    }
}

