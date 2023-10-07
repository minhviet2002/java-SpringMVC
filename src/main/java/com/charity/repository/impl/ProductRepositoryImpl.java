/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.repository.impl;

import com.charity.pojo.Category;
import com.charity.pojo.Product;
import com.charity.pojo.User;
import com.charity.repository.ProductRepository;
import com.cloudinary.utils.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public boolean addOrUpdateProduct(Product p, User u) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            p.setActive(Boolean.TRUE);
            p.setUserCreated(u);
            p.setCreatedDate(new Date());
            if (p.getIdproduct() == null) {
                s.save(p);
            } else {
                s.update(p);
            }
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
    
    @Override
    public Product getProductById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Product.class, id);
    }
    
    @Override
    public List<Product> getProducts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = b.createQuery(Product.class);
        Root root = q.from(Product.class);
        q.select(root);
        
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }
            
            String cateId = params.get("cateId");
            if (cateId != null && !cateId.isEmpty()) {
                predicates.add(b.equal(root.get("categoryId"), Integer.parseInt(cateId)));
            }
            q.where(predicates.toArray(Predicate[]::new));
        }
        
        q.orderBy(b.desc(root.get("idproduct")));
        
        Query query = s.createQuery(q);
        
        if (params != null) {
            String page = params.get("page");
            int pageSize = Integer.parseInt("3");
            if (page != null) {
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }
        return query.getResultList();
        
    }
    
    @Override
    public boolean deleteProductById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(this.getProductById(id));
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
    
    @Override
    public List<Product> getProductsByIdCate(int id, Map<String, String> params) {
//        Session s = this.factory.getObject().getCurrentSession();
//        String hql = "SELECT p FROM Product p WHERE p.categoryId.idcategory = :categoryId";
//        Query q = s.createQuery(hql);
//        q.setParameter("categoryId", id);
//        return q.getResultList();
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = b.createQuery(Product.class);
        Root root = q.from(Product.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("categoryId"), id));
        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }
            q.where(predicates.toArray(Predicate[]::new));
        }
        
        q.orderBy(b.desc(root.get("idproduct")));
        
        Query query = s.createQuery(q);
        
        if (params != null) {
            String page = params.get("page");
            int pageSize = Integer.parseInt("3");
            if (page != null) {
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }
        return query.getResultList();
    }
    
    @Override
    public List<Product> getProductsByIdUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT p FROM Product p WHERE p.userCreated.iduser = :id";
        Query q = s.createQuery(hql);
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    @Override
    public boolean updateProductActive(Product p) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.saveOrUpdate(p);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
    
    @Override
    public int countProduct() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) From Product");
        return Integer.parseInt(q.getSingleResult().toString());
    }
    
    @Override
    public int countProductByIdCate(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT COUNT(p) From Product p WHERE p.categoryId.idcategory = :id";
        Query q = s.createQuery(hql);
        q.setParameter("id", id);
        return Integer.parseInt(q.getSingleResult().toString());
    }
    
}
