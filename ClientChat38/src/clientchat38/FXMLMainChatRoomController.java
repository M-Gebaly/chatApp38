/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat38;

import common.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author M.Gebaly
 */
public class FXMLMainChatRoomController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    User user = null;
    
    @FXML
    private Label userNameLable;
    
    @FXML ComboBox statusBox;
    
    ObservableList<String> statusList = FXCollections.observableArrayList("online", "offline", "busy");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                userNameLable.setText(user.getName());
                statusBox.setItems(statusList);
            }
        });
    }    
    
    public void setUser(User user){
        this.user = user;
    }
}
