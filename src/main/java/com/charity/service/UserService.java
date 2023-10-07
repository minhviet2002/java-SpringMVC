/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.service;

import com.charity.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ASUS
 */
public interface UserService extends UserDetailsService {

    User getUserByUsername(String username);

    User getUserById(int id);

    boolean addUser(User user);

    public List<User> getUsers();
    
    public boolean updateUser(User user);
}
