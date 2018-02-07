/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;

/**
 *
 * @author Asmaa
 */
public interface ServerInterface {
    public boolean addNewUser(User user);
    public User login(String email,String password);
    public ArrayList<User> getFriendList(String email);
    
}
