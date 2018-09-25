package moodleproject;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MD_Kaykobad_Reza
 */
public class RegisterController implements Initializable {
    
    public String lev = "";
    public String tr = "";
    
    @FXML
    private Label lb_welcome;
    @FXML
    private Label lb_name;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_stid;
    @FXML
    private PasswordField pf_password;
    @FXML
    private TextField tf_username;
    @FXML
    private MenuButton level_m;
    @FXML
    private RadioMenuItem Level_1;
    @FXML
    private RadioMenuItem Level_2;
    @FXML
    private RadioMenuItem Level_3;
    @FXML
    private RadioMenuItem Level_4;
    @FXML
    private MenuButton term_m;
    @FXML
    private RadioMenuItem Term_1;
    @FXML
    private RadioMenuItem Term_2;
    @FXML
    private Label lb_email;
    @FXML
    private Label lb_stid;
    @FXML
    private Label lb_username;
    @FXML
    private Label lb_pass;
    @FXML
    private Label lb_lvterm;
    @FXML
    private Label lb_subjects;
    @FXML
    private CheckBox oop_ck;
    @FXML
    private CheckBox algorithm_ck;
    @FXML
    private CheckBox machine_ck;
    @FXML
    private CheckBox dld_ck;
    @FXML
    private CheckBox ds_ck;
    @FXML
    private CheckBox network_ck;
    @FXML
    private Button btn_register;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void level1Action(ActionEvent e){
        lev = "1";
    }
    public void level2Action(ActionEvent e){
        lev = "2";
    }
    public void level3Action(ActionEvent e){
        lev = "3";
    }
    public void level4Action(ActionEvent e){
        lev = "4";
    }
    public void term1Action(ActionEvent e){
        tr = "1";
    }
    public void term2Action(ActionEvent e){
        tr = "2";
    }    
    
    public void registerAction(ActionEvent e){
        String uname, pass, fname, email, stid, level, trm, totalinfo;
        uname = tf_username.getText();
        pass = pf_password.getText();
        fname = tf_name.getText();
        email = tf_email.getText();
        stid = tf_stid.getText();
        
        totalinfo = "reg:"+uname+":"+pass+":"+fname+":"+email+":"+stid+":"+lev+":"+tr;
        
        try {
            MoodleProject.dout.writeUTF(totalinfo);
            System.out.println(totalinfo);
            Parent homepage = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene FirstScene = new Scene(homepage);
            Stage stg = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stg.setScene(FirstScene);
            stg.show();
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
