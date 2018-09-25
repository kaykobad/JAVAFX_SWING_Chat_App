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
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MD_Kaykobad_Reza
 */
public class HomePageController implements Initializable {
    @FXML
    private TextArea ta_text;
    @FXML
    private TextField tf_post;
    @FXML
    private Button btn_post;
    @FXML
    private Button btn_home;
    @FXML
    private Button btn_recent;
    @FXML
    private Button btn_faculty;
    @FXML
    private Button btn_stlist;
    @FXML
    private CheckMenuItem algo;
    @FXML
    private CheckMenuItem ds;
    @FXML
    private CheckMenuItem dld;
    @FXML
    private CheckMenuItem oop;
    @FXML
    private CheckMenuItem machine;
    @FXML
    private CheckMenuItem network;
    @FXML
    private Button btn_aboutus;
    @FXML
    private Button btn_online;
    @FXML
    private Button btn_logout;
    @FXML
    private Label lb_welcome;
    @FXML
    private Label lbl_indicator;
    
    private int subjectIndicator = 0;
    /**
     * object oriented programming = 1
     * logic = 2
     * machine = 3
     * network = 4
     * data structure = 5
     * algorithm = 6
     */

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            MoodleProject.dout.writeUTF("home:request");
            String home = null;
            home = MoodleProject.din.readUTF();
            lbl_indicator.setText("Home Page");
            ta_text.setText(home);
            subjectIndicator = 0;
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void logoutAction(ActionEvent e)throws Exception{
        try {
            subjectIndicator = 0;
            MoodleProject.dout.writeUTF("logout:"+MoodleProject.fullname);
            Parent homepage = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene FirstScene = new Scene(homepage);
            Stage stg = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stg.setScene(FirstScene);
            stg.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void studentListAction(ActionEvent e){
        try {
            subjectIndicator = 0;
            MoodleProject.dout.writeUTF("studentlist:request");
            String message = null;
            message = MoodleProject.din.readUTF();
            lbl_indicator.setText("Student List");
            ta_text.setText(message);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onlineUserAction(ActionEvent e){
        try {
            subjectIndicator = 0;
            MoodleProject.dout.writeUTF("online:request");
            String message = null;
            message = MoodleProject.din.readUTF();
            lbl_indicator.setText("Online Users");
            ta_text.setText(message);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void aboutUsAction(ActionEvent e){
        try {
            subjectIndicator = 0;
            MoodleProject.dout.writeUTF("aboutus:request");
            String aboutus = null;
            aboutus = MoodleProject.din.readUTF();
            lbl_indicator.setText("About Us");
            ta_text.setText(aboutus);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void facultyAction(ActionEvent e){
        try {
            subjectIndicator = 0;
            MoodleProject.dout.writeUTF("faculty:request");
            String faculty = null;
            faculty = MoodleProject.din.readUTF();
            lbl_indicator.setText("Faculty List");
            ta_text.setText(faculty);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void homePageAction(ActionEvent e){
        try {
            subjectIndicator = 0;
            MoodleProject.dout.writeUTF("home:request");
            String home = null;
            home = MoodleProject.din.readUTF();
            lbl_indicator.setText("Home Page");
            ta_text.setText(home);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dldAction(ActionEvent e){
        try {
            subjectIndicator = 2;
            MoodleProject.dout.writeUTF("course:dld:request");
            String home = null;
            home = MoodleProject.din.readUTF();
            lbl_indicator.setText("Digital Logic Design");
            ta_text.setText(home);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void algoAction(ActionEvent e){
        try {
            subjectIndicator = 6;
            MoodleProject.dout.writeUTF("course:algo:request");
            String home = null;
            home = MoodleProject.din.readUTF();
            lbl_indicator.setText("Algorithm");
            ta_text.setText(home);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void oopAction(ActionEvent e){
        try {
            subjectIndicator = 1;
            MoodleProject.dout.writeUTF("course:oop:request");
            String home = null;
            home = MoodleProject.din.readUTF();
            lbl_indicator.setText("OOP");
            ta_text.setText(home);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void machineAction(ActionEvent e){
        try {
            subjectIndicator = 3;
            MoodleProject.dout.writeUTF("course:machine:request");
            String home = null;
            home = MoodleProject.din.readUTF();
            lbl_indicator.setText("Machine Learning");
            ta_text.setText(home);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void networkAction(ActionEvent e){
        try {
            subjectIndicator = 4;
            MoodleProject.dout.writeUTF("course:network:request");
            String home = null;
            home = MoodleProject.din.readUTF();
            lbl_indicator.setText("Computer Networking");
            ta_text.setText(home);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dsAction(ActionEvent e){
        try {
            subjectIndicator = 5;
            MoodleProject.dout.writeUTF("course:ds:request");
            String home = null;
            home = MoodleProject.din.readUTF();
            lbl_indicator.setText("Data Structure");
            ta_text.setText(home);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void postAction(ActionEvent e){
        String post = "post:";
        if(subjectIndicator==1) post +="oop:";
        else if(subjectIndicator==2) post +="dld:";
        else if(subjectIndicator==3) post +="machine:";
        else if(subjectIndicator==4) post +="network:";
        else if(subjectIndicator==5) post +="ds:";
        else if(subjectIndicator==6) post +="algo:";
        
        post +=MoodleProject.fullname+"("+MoodleProject.status+"):-"+tf_post.getText();
        ta_text.setText(ta_text.getText()+"\n"+MoodleProject.fullname+"("+MoodleProject.status+")-"+tf_post.getText());
        tf_post.setText("");
        try {
            MoodleProject.dout.writeUTF(post);
            String s = MoodleProject.din.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void profileAction(ActionEvent e){
        String p = "profile:"+MoodleProject.name;
        try {
            MoodleProject.dout.writeUTF(p);
            p = MoodleProject.din.readUTF();
            lbl_indicator.setText("My Profile");
            ta_text.setText("My information: \n\n"+p);
            
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void commentAction(ActionEvent e){
        if(subjectIndicator!=0){
            String s="comment:";
            switch(subjectIndicator){
                case 1:
                    s += "oop:";
                    break;
                case 2:
                    s += "dld:";
                    break;
                case 3:
                    s += "machine:";
                    break;
                case 4:
                    s += "network:";
                    break;
                case 5:
                    s += "ds:";
                    break;
                case 6:
                    s += "algo:";
                    break;
            }
            String p = ta_text.getText();
            try {
                s += ta_text.getText();
                MoodleProject.dout.writeUTF(s);
                System.out.println(s);
                String q = MoodleProject.din.readUTF();
                ta_text.setText(p);

            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void refreshAction(ActionEvent e){
        if(subjectIndicator!=0){
            String s="course:";
            switch(subjectIndicator){
                case 1:
                    s += "oop:request";
                    break;
                case 2:
                    s += "dld:request";
                    break;
                case 3:
                    s += "machine:request";
                    break;
                case 4:
                    s += "network:request";
                    break;
                case 5:
                    s += "ds:request";
                    break;
                case 6:
                    s += "algo:request";
                    break;
            }
            
            try {
                MoodleProject.dout.writeUTF(s);
                String p = MoodleProject.din.readUTF();
                ta_text.setText(p);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
