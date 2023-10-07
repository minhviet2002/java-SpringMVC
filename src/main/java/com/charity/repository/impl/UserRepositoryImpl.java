/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.repository.impl;

import com.charity.pojo.User;
import com.charity.repository.UserRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User U WHERE U.username = :username");

        if (!username.isEmpty()) {
            q.setParameter("username", username);
        }
        return (User) q.getSingleResult();

    }

    @Override
    public boolean addUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (user.getIduser() == null) {
                s.save(user);
            } else {
                s.update(user);
            }
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User U WHERE U.iduser = :id");
        q.setParameter("id", id);
        return (User) q.getSingleResult();
    }

    @Override
    public List<User> getUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From User");
        return q.getResultList();
    }

    @Override
    public boolean updateUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(user);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

}
