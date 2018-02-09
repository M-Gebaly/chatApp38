/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asmaa
 */
public class serverImpl extends UnicastRemoteObject implements common.ServerInterface {

    public serverImpl() throws RemoteException{
        
    }

    
    @Override
    public boolean addNewUser(User user) throws RemoteException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean result = false;
        user.setStatus("avalable");
        user.setStatusFlag("true");
        DatabaseHandlerImp databaseHandlerImp = new DatabaseHandlerImp();
        try {
            System.out.println("user here");
            result = databaseHandlerImp.insertUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(serverImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;    
    }

    @Override
    public User login(String email, String password) throws RemoteException{
        try {
            DatabaseHandlerImp databaseHandlerImp = new DatabaseHandlerImp();
            User user = databaseHandlerImp.loginHandler(email, password);
            if (user!= null)
            {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(serverImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("user not found");
        return null;
    }

    @Override
    public ArrayList<User> getFriendList(String email) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
