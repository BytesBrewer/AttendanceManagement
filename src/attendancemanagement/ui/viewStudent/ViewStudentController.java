package attendancemanagement.ui.viewStudent;

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

public class ViewStudentController implements Initializable {

    ObservableList<Student> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> grNoCol;
    @FXML
    private TableColumn<Student, String> yearCol;
    @FXML
    private TableColumn<Student, String> branchCol;
    @FXML
    private TableColumn<Student, String> fatherCol;
    @FXML
    private TableColumn<Student, String> motherCol;
    @FXML
    private TableColumn<Student, String> genderCol;
    @FXML
    private TableColumn<Student, String> emailCol;
    @FXML
    private TableColumn<Student, String> phoneCol;
    @FXML
    private TableColumn<Student, String> dobCol;
    @FXML
    private TableColumn<Student, String> addressCol;
    @FXML
    private TableView<Student> StudentTable;
    @FXML
    private TableColumn<Student, Integer> rollNoCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initCol();
        loadData();
    }    
    
    private void initCol()
    {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        grNoCol.setCellValueFactory(new PropertyValueFactory<>("grNo"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        rollNoCol.setCellValueFactory(new PropertyValueFactory<>("rollNo"));
        fatherCol.setCellValueFactory(new PropertyValueFactory<>("father"));
        motherCol.setCellValueFactory(new PropertyValueFactory<>("mother"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        branchCol.setCellValueFactory(new PropertyValueFactory<>("branch"));
    }
    
    private void loadData()
    {
        DatabaseHandler database = new DatabaseHandler();
        String qu = "SELECT * FROM STUDENT";
        ResultSet rs = database.execQuery(qu);
        try
        {
            while(rs.next())
            {
                String namex = rs.getString("firstName");
                namex = namex.concat(" ");
                namex = namex.concat(rs.getString("lastName"));
                String grNox = rs.getString("grno");
                String yearx = rs.getString("class");
                Integer rollNox = rs.getInt("roll");
                String branchx = rs.getString("branch");
                String fatherx = rs.getString("father");
                String motherx = rs.getString("mother");
                String genderx = rs.getString("gender");
                String emailx = rs.getString("email");
                String phonex = rs.getString("number");
                String dobx = rs.getString("dob");
                String addressx = rs.getString("address");
                
                list.add(new Student(namex , grNox , rollNox , yearx , branchx , fatherx , motherx , genderx , emailx , phonex , dobx , addressx));
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ViewStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        StudentTable.getItems().setAll(list);
    }
    public static class Student
    {
        private final SimpleStringProperty name;
        private final SimpleStringProperty grNo;
        private final SimpleIntegerProperty rollNo;
        private final SimpleStringProperty year;
        private final SimpleStringProperty branch;
        private final SimpleStringProperty father;
        private final SimpleStringProperty mother;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty email;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty dob;
        private final SimpleStringProperty address;
        
        public Student(String name ,String grNo ,Integer rollNo ,String year ,String branch ,String father ,String mother ,String gender ,String email ,String phone ,String dob ,String address)
        {
            this.name = new SimpleStringProperty(name);
            this.grNo = new SimpleStringProperty(grNo);
            this.rollNo = new SimpleIntegerProperty(rollNo);
            this.year = new SimpleStringProperty(year);
            this.branch = new SimpleStringProperty(branch);
            this.father = new SimpleStringProperty(father);
            this.mother = new SimpleStringProperty(mother);
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
        public String getGrNo()
        {
            return grNo.get();
        }
        public int getRollNo()
        {
            return rollNo.get();
        }
        public String getYear()
        {
            return year.get();
        }
        public String getBranch()
        {
            return branch.get();
        }
        public String getFather()
        {
            return father.get();
        }
        public String getMother()
        {
            return mother.get();
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
    }
}
