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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author M.Gebaly
 */
public class FXMLRegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField rname;

    @FXML
    private TextField rpassword;

    @FXML
    private TextField remail;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private Button signup_btn;

    @FXML
    private AnchorPane registerPane;

    ServerInterface server;

    public FXMLRegistrationController() {
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

    }

    @FXML
    private void signUpAction(ActionEvent event) {
        String name = rname.getText();
        String Password = rpassword.getText();
        String email = remail.getText();

        boolean isMale = male.isSelected();
        boolean isFemale = female.isSelected();

        if (name.equals("") || Password.equals("") || email.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Look, a Warning Dialog");

            if (name.equals("")) {
                alert.setContentText("You must enter your name");
                alert.showAndWait();
            }
            if (Password.equals("")) {
                alert.setContentText("You must enter your password");
                alert.showAndWait();
            }
            if (email.equals("")) {
                alert.setContentText("You must enter your email");
                alert.showAndWait();
            }

        } else if (!isFemale && !isMale) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("You must enter your Ginder");
            alert.showAndWait();
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(Password);
            user.setEmail(email);
            if (isMale) {
                user.setGender("male");
            }
            if (isFemale) {
                user.setGender("female");
            }
            boolean result;
            try {
                result = server.addNewUser(user);
                if (!result) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Look, a Warning Dialog");
                    alert.setContentText("Falied");
                    alert.showAndWait();
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLStart.fxml"));
                        Parent root = loader.load();
                        FXMLStartController controller = loader.getController();
                        controller.setEmail(remail.getText());
                        

                        Scene scene = new Scene(root);

                        Stage stage = (Stage) registerPane.getScene().getWindow();
                        stage.setTitle("Login Page");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FXMLRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
