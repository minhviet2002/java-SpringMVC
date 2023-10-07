/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.repository.impl;

import com.charity.pojo.Post;
import com.charity.pojo.Product;
import com.charity.repository.PostRepository;
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
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Post> getPosts() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT p From Post p ORDER BY p.createdDate DESC");

        return q.getResultList();
    }

    @Override
    public Post getPostById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Post.class, id);
    }

    @Override
    public boolean addOrUpdatePost(Post p) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            p.setCreatedDate(new Date());
            s.saveOrUpdate(p);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Post> getPostsByIdUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT p FROM Post p WHERE p.iduser.iduser = :id";
        Query q = s.createQuery(hql);
        q.setParameter("id", id);
        return q.getResultList();
    }

    @Override
    public boolean deletePostById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(this.getPostById(id));
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Object[]> countCmtLike() {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT p.idpost AS id, COUNT(cp.idpost) AS countCmt, COUNT(lp.idpost) AS countLike "
                + "FROM Post p LEFT JOIN CommentPost cp ON p.idpost = cp.idpost"
                + " LEFT JOIN LikePost lp ON p.idpost = lp.idpost "
                + "GROUP BY p.idpost";
        Query q = s.createQuery(hql);
        return q.getResultList();
    }

}
