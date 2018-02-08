/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverchat38;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import controller.serverImpl;
/**
 *
 * @author M.Gebaly
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;

    public FXMLDocumentController() {

    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Registry registry = LocateRegistry.createRegistry(2000);
            registry.rebind("chat", new serverImpl());

        }
        catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
