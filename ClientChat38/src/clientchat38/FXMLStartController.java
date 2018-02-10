/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat38;

import common.ServerInterface;
import common.User;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author M.Gebaly
 */
public class FXMLStartController implements Initializable {

//    @FXML
//    private Label label;
//    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    @FXML
    private Hyperlink goToRegist;
    
    @FXML
    private AnchorPane loginPane;
    ServerInterface server = null;
    @FXML
    public TextField email;
    @FXML
    public TextField password;
    @FXML
    public Button loginpBtn;
    
    private String emailpar = "";
    
    public FXMLStartController() {
        try {
            Registry registry = LocateRegistry.getRegistry(2000);
            server = (ServerInterface) registry.lookup("chat");
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(FXMLRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                email.setText(emailpar);
                password.setText("");
            }
        });
    }
    
    @FXML
    private void loginAction(ActionEvent event) {
        String emailv = email.getText();
        String passwordv = password.getText();
        if (!emailv.equals("") && !passwordv.equals("")) {
            try {
                User user;
                user = server.login(email.getText(), password.getText());
                if (user != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainChatRoom.fxml"));
                        Parent root = loader.load();
                        FXMLMainChatRoomController controller = loader.getController();
                        //controller.setEmail(remail.getText());
                        controller.setUser(user);

                        Scene scene = new Scene(root);

                        Stage stage = (Stage) loginPane.getScene().getWindow();
                        stage.setTitle("Chat Page");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Look, an Information Dialog");
                    alert.setContentText("Incorrect password or email");
                    alert.showAndWait();
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FXMLRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Look, a Warning Dialog");
            if (email.equals("")) {
                alert.setContentText("You must enter your email");
                alert.showAndWait();
            }
            if (password.equals("")) {
                alert.setContentText("You must enter your password");
                alert.showAndWait();
            }
            
            
        }
    }
    
    @FXML
    private void registerAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRegistration.fxml"));
            Parent root = loader.load();
            FXMLRegistrationController controller = loader.getController();
            //controller.setText(nameTxtField.getText());

            Scene scene = new Scene(root);
            
            Stage stage = (Stage) loginPane.getScene().getWindow();
            stage.setTitle("Register Page");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setEmail(String email) {
        emailpar = email;
    }
    
}
