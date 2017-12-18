
package attendancemanagement.ui.login;

import attendancemanagement.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLDocumentController implements Initializable {

    Effect blur = new GaussianBlur();
    Effect drop = new DropShadow();
    @FXML
    private JFXRadioButton rb1;
    @FXML
    private ToggleGroup Mygroup;
    @FXML
    private JFXRadioButton rb2;
    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;
    
    DatabaseHandler databaseHandler;
    @FXML
    private StackPane StackPane;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton login;
    @FXML
    private Pane Pane;
    @FXML
    private ImageView image;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        databaseHandler = new DatabaseHandler();
        StackPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 20;");
    }    
    
    @FXML
    public void Login(ActionEvent event) throws IOException
    {
        try {
            String qu = "SELECT USERNAME, PASSWORD FROM FACULTY WHERE username='"+txtusername.getText()+"' AND password='"+txtpassword.getText()+"'";
            ResultSet rs = databaseHandler.execQuery(qu);
            rs.next();
            if(rb1.isSelected() && txtusername.getText().equals("admin") && txtpassword.getText().equals(rs.getString("password")))
            {
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage=new Stage(StageStyle.TRANSPARENT);
                Parent root = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/adminLogin/AdminLogin.fxml"));
                
                Scene scene = new Scene(root);
                
                stage.setScene(scene);
                stage.show();
            }
            else if(rb2.isSelected() && txtusername.getText().equals(rs.getString("username")) && txtpassword.getText().equals(rs.getString("password")))
            {
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/attendancemanagement/ui/faculty_Login/FacultyLogin.fxml"));               
                System.out.println("fxml loaded");
                Scene scene = new Scene(root);              
                stage.setScene(scene);
                stage.show();                
            }
            else
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Invalid Username or Password!!");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void close(ActionEvent event) 
    {
        StackPane.setEffect(blur);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you want to exit??");
        //alert.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
                ((Node)(event.getSource())).getScene().getWindow().hide();
        else
            StackPane.setEffect(null);
    }

    @FXML
    private void hover(MouseEvent event) 
    {
        if(event.getSource().equals(login))
        {
            login.setEffect(drop);
        }
        else if(event.getSource().equals(exit))
        {
            exit.setEffect(drop);
        }
    }

    @FXML
    private void hover_close(MouseEvent event) 
    {
        if(event.getSource().equals(image))
        {
            login.setEffect(null);
            exit.setEffect(null);
        }
    }
    
}
