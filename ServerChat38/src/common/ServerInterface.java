/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Asmaa
 */
public interface ServerInterface extends Remote{
    public boolean addNewUser(User user)throws RemoteException;
    public User login(String email,String password)throws RemoteException;
    public ArrayList<User> getFriendList(int id)throws RemoteException;
    
}
