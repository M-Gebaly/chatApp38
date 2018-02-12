/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat38;

import common.ServerInterface;
import common.User;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;

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
    
    @FXML
    private ListView frindListView;
    ServerInterface server = null;
    
    ObservableList<String> statusList = FXCollections.observableArrayList("online", "offline", "busy");
    ArrayList<User> frindList = new ArrayList<>();
    ObservableList<User> oFrindList;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry registry = LocateRegistry.getRegistry(2000);
            server = (ServerInterface) registry.lookup("chat");
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(FXMLRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // TODO
                    frindList = server.getFriendList((int) user.getId());
                } catch (RemoteException ex) {
                    Logger.getLogger(FXMLMainChatRoomController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                userNameLable.setText(user.getName());
                statusBox.setItems(statusList);
                oFrindList = FXCollections.observableArrayList(frindList);
                frindListView.setItems( oFrindList);
                loadFrindList();
            }
        });
        
    }    
    
    public void setUser(User user){
        this.user = user;
    }
    void loadFrindList() {
        frindListView.setCellFactory(listView -> new ListCell<User>() {

            @Override
            public void updateItem(User friend, boolean empty) {
                super.updateItem(friend, empty);
                if (empty || friend == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setGraphic(loadCell(friend));
                }
            }

        });
    }
    
    Node loadCell(User friend) {
        ImageView imageView = new ImageView();
        //ImageView imageViewStatus = new ImageView();

        FlowPane flow = new FlowPane();
        flow.setHgap(4);
        flow.setPrefWidth(1);
        
        Label friendName = new Label();
        friendName.setText(friend.getName());
        
        Image image = new Image("/img/userIcon.png", true);
        imageView.setImage(image);
        imageView.setFitWidth(35);
        imageView.setFitHeight(35);
        
        flow.getChildren().addAll(imageView,friendName);
        return flow;
    }
}
