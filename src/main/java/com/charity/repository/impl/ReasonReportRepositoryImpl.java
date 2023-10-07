/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.repository.impl;

import com.charity.pojo.Category;
import com.charity.pojo.Product;
import com.charity.pojo.Reason;
import com.charity.pojo.ReasonReport;
import com.charity.pojo.User;
import com.charity.repository.ProductRepository;
import com.charity.repository.ReasonReportRepository;
import com.charity.repository.ReasonRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
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
public class ReasonReportRepositoryImpl implements ReasonReportRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean addReasonReport(ReasonReport r) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            r.setCreatedDate(new Date());
            s.save(r);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public int countReportByIdProduct(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Product p = this.productRepository.getProductById(id);
        String hql = "SELECT COUNT(r) FROM ReasonReport r WHERE r.iduserreported.iduser = :iduser";
        Query q = s.createQuery(hql);
        q.setParameter("iduser", p.getUserCreated().getIduser());

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public List<ReasonReport> getReasonReportByIdUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT r FROM ReasonReport r WHERE r.iduserreported.iduser = :iduser";
        Query q = s.createQuery(hql);
        q.setParameter("iduser", id);
        return q.getResultList();
    }

}
