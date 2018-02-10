/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import common.User;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author M.Gebaly
 */
public interface DatabaseHandler {
    boolean insertUser(User user) throws SQLException;
    User loginHandler(String email, String password) throws SQLException;
    ArrayList<User> friendListHandler(long id) throws SQLException;
    boolean update(User user)throws SQLException;
}
