/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverchat38;

import controller.DatabaseHandlerImp;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import oracle.jdbc.OracleDriver;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author Mark
 */
public class PieChartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn;
    ResultSet rs;
    PreparedStatement prst;  
    @FXML
    public AnchorPane anchorPane;
    public PieChartController() {
        try {
        DriverManager.registerDriver(new OracleDriver());
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "chat","chat");
        System.out.println("connection succeeded");
        } catch (SQLException ex) {
        Logger.getLogger(DatabaseHandlerImp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            prst = conn.prepareStatement("select gender from users");        
            rs = prst.executeQuery();
            int maleUsers = 0;
            int femaleUsers = 0;
                while (rs.next()) {                
                    if (rs.getString("GENDER").equals("male"))
                    {
                        maleUsers++;
                        System.out.println(maleUsers);
                    }
                    else
                    {
                        femaleUsers++;
                    }
                }               
         ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Male", maleUsers),
                new PieChart.Data("Female", femaleUsers));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Users");
        anchorPane.getChildren().add(chart);
        } catch (SQLException ex) {
            Logger.getLogger(PieChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
