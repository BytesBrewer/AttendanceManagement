package attendancemanagement.ui.adminLogin;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminLoginController implements Initializable {

    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton add_faculty;
    @FXML
    private JFXButton add_student;
    @FXML
    private JFXButton manage_faculty;
    @FXML
    private JFXButton manage_student;
    @FXML
    private JFXButton view_student;
    @FXML
    private JFXButton view_faculty;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private BorderPane borderPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    

    @FXML
    private void Logout(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/login/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void AddStudent(ActionEvent event) throws IOException
    {
        AnchorPane addStudent = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/addStudent/AddStudent.fxml"));
        borderPane.setCenter(addStudent);
    }

    @FXML
    private void AddFaculty(ActionEvent event) throws IOException
    {
        AnchorPane addFaculty = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/addFaculty/AddFaculty.fxml"));
        borderPane.setCenter(addFaculty);
    }

    @FXML
    private void ViewStudent(ActionEvent event) throws IOException 
    {
        AnchorPane viewStudent = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/viewStudent/ViewStudent.fxml"));
        borderPane.setCenter(viewStudent);
    }

    @FXML
    private void ViewFaculty(ActionEvent event) throws IOException 
    {
        AnchorPane viewFaculty = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/viewFaculty/ViewFaculty.fxml"));
        borderPane.setCenter(viewFaculty);
    }

    @FXML
    private void manageFaculty(ActionEvent event) throws IOException 
    {
        AnchorPane manageFaculty = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/manageFaculty/ManageFaculty.fxml"));
        borderPane.setCenter(manageFaculty);
    }

    @FXML
    private void manageStudent(ActionEvent event) throws IOException 
    {
        AnchorPane manageStudent = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/manageStudent/ManageStudent.fxml"));
        borderPane.setCenter(manageStudent);
    }

    @FXML
    private void hover(MouseEvent event) 
    {
        if(event.getSource().equals(add_faculty))
            add_faculty.setStyle("-fx-background-color: #EEEEEE;");
        else if(event.getSource().equals(add_student))
            add_student.setStyle("-fx-background-color: #EEEEEE;");
        else if(event.getSource().equals(view_faculty))
            view_faculty.setStyle("-fx-background-color: #EEEEEE;");
        else if(event.getSource().equals(view_student))
            view_student.setStyle("-fx-background-color: #EEEEEE;");
        else if(event.getSource().equals(manage_faculty))
            manage_faculty.setStyle("-fx-background-color: #EEEEEE;");
        else if(event.getSource().equals(manage_student))
            manage_student.setStyle("-fx-background-color: #EEEEEE;");
        else if(event.getSource().equals(logout))
            logout.setStyle("-fx-background-color: #EEEEEE;");
    }

    @FXML
    private void hover_close(MouseEvent event) 
    {
        if(event.getSource().equals(add_faculty))
            add_faculty.setStyle("-fx-background-color: #ffffff;");
        else if(event.getSource().equals(add_student))
            add_student.setStyle("-fx-background-color: #ffffff;");
        else if(event.getSource().equals(view_faculty))
            view_faculty.setStyle("-fx-background-color: #ffffff;");
        else if(event.getSource().equals(view_student))
            view_student.setStyle("-fx-background-color: #ffffff;");
        else if(event.getSource().equals(manage_faculty))
            manage_faculty.setStyle("-fx-background-color: #ffffff;");
        else if(event.getSource().equals(manage_student))
            manage_student.setStyle("-fx-background-color: #ffffff;");
        else if(event.getSource().equals(logout))
            logout.setStyle("-fx-background-color: #ffffff;");
    }
    
}
