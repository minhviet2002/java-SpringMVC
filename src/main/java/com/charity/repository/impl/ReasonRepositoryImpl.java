/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.repository.impl;

import com.charity.pojo.Reason;
import com.charity.repository.ReasonRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class ReasonRepositoryImpl implements ReasonRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Reason> getReasons() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Reason");
        return q.getResultList();
    }

}
