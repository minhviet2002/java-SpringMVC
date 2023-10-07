/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.repository.impl;

import com.charity.pojo.Category;
import org.hibernate.Session;
import com.charity.repository.CategoryRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Category> getCategory() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Category");
        return q.getResultList();
    }

    @Override
    public boolean addOrUpdateCategory(Category c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            c.setCreatedDate(new Date());
            s.saveOrUpdate(c);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Category getCategoryById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Category.class, id);
    }

    @Override
    public boolean deleteCateById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(this.getCategoryById(id));
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Object[]> countProductCate() {
        Session s = this.factory.getObject().getCurrentSession();
//        String hql = "SELECT p.categoryId.idcategory, c.name, COUNT(p) AS countProduct FROM Product p JOIN p.categoryId c GROUP BY p.categoryId.idcategory";
        String hql = "SELECT c.idcategory, c.name, COUNT(p.idproduct) AS countProduct FROM Category c LEFT JOIN Product p ON c.idcategory = p.categoryId.idcategory GROUP BY c.idcategory, c.name";
        Query q = s.createQuery(hql);
        return q.getResultList();
    }
}
