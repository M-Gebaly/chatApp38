/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat38;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registerAction(ActionEvent event){
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
    

}
