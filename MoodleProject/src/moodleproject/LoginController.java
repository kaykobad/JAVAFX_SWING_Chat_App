/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MD_Kaykobad_Reza
 */
public class LoginController implements Initializable {
    @FXML
    private Label lb_welcome;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_pasword;
    @FXML
    private Button btn_login;
    @FXML
    private Label lbl_warning;
    @FXML
    private Button btn_register;
    @FXML
    private Label lbl_loginas;
    @FXML
    private TextField tf_loginas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void loginButtonAction(ActionEvent e){

        String data = "login:" + tf_username.getText()+":"+pf_pasword.getText()+":"+tf_loginas.getText();
        MoodleProject.name = tf_username.getText();
        MoodleProject.status = tf_loginas.getText();
        System.out.println(tf_username.getText()+":"+pf_pasword.getText()+":"+MoodleProject.status+" Login successfull...");
        
        try {
            MoodleProject.dout.writeUTF(data);
            MoodleProject.fullname = MoodleProject.din.readUTF();
            
            if(!(MoodleProject.fullname.equals("false"))){
                Parent homepage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene FirstScene = new Scene(homepage);
                Stage stg = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stg.setScene(FirstScene);
                stg.show();
            }
            else{
                lbl_warning.setText("Invalid username or password...");
                
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void registerButtonAction(ActionEvent e){
        System.out.println("Trying to register");
        try {
            System.out.println(tf_username.getText() + pf_pasword.getText());
            Parent register = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Scene FirstScene = new Scene(register);
            Stage stg = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stg.setScene(FirstScene);
            stg.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
