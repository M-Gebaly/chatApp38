/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import oracle.jdbc.OracleDriver;
import oracle.jdbc.driver.*;

/**
 *
 * @author M.Gebaly
 */
public class DatabaseHandlerImp implements DatabaseHandler {

    Connection conn;
    ResultSet rs;
    PreparedStatement prst;

    public DatabaseHandlerImp() {
        try {
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chat", "chat");
            System.out.println("connection succeeded");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandlerImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean insertUser(User user) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "INSERT INTO users(NAME,EMAIL,PASSWORD,GENDER,STATUS,STATUSFLAG) VALUES (?,?,?,?,?,?)";
        String query = "select email from Users where email = '" + user.getEmail() + "'";
        try {
            prst = conn.prepareStatement(query);
            rs = prst.executeQuery();
            if (rs.next()) {
                return false;
            } else{
                prst = conn.prepareStatement(sql);
                //prst.setObject(1, user.getId());
                prst.setObject(1, user.getName());
                prst.setObject(2, user.getEmail());
                prst.setObject(3, user.getPassword());
                prst.setObject(4, user.getGender());
                prst.setObject(5, user.getStatus());
                prst.setObject(6, user.getStatusFlag());

                prst.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(serverImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public User loginHandler(String email, String password) throws SQLException {
        prst = conn.prepareStatement("Select * from USERS where email = '" + email + "' and password = '" + password + "'");
        rs = prst.executeQuery();
        boolean unique = false;
        while (rs.next()) {
            if (rs.getString(2).equalsIgnoreCase(email)) {
                unique = true;
                break;
            }
        }
        //prst.close();
        if (unique) {
            return new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7));
        }
        return null;
    }

    @Override
    public ArrayList<User> friendListHandler(long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
