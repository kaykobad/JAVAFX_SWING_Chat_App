package moodleproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MD_Kaykobad_Reza
 */
public class MoodleProject extends Application {
    
    public static Socket socket;
    public static int port = 2222;
    public static String address = "localhost";
    public static DataInputStream din;
    public static DataOutputStream dout;
    public static String name;
    public static String status;
    public static String fullname;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MoodleProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            socket = new Socket(address, port);
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            //launch(args);
        } catch (IOException ex) {
            System.out.println("Connection failed...");
            Logger.getLogger(MoodleProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(args);
    }
    
}
