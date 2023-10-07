/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.repository;

import com.charity.pojo.User;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface UserRepository {

    User getUserByUsername(String username);

    User getUserById(int id);

    boolean addUser(User user);
    
    boolean updateUser(User user);

    public List<User> getUsers();
}
