
package attendancemanagement.ui.faculty_Login;

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
import javafx.stage.Stage;


public class FacultyLoginController implements Initializable {

    @FXML
    private JFXButton takeAtten;
    @FXML
    private JFXButton viewAtten;
    @FXML
    private JFXButton logout;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void takeAtten(ActionEvent event) throws IOException 
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/takeAtten/TakeAtten.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ViewAtten(ActionEvent event) throws IOException 
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/viewAtten/ViewAttendance.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
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
    
}
