package com.charity.repository.impl;

import com.charity.pojo.Auction;
import com.charity.repository.AuctionRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ASUS WHERE a .idproduct = :idproduct q.setParameter("idproduct", id);
 */
@Repository
@Transactional
public class AuctionRepositoryImpl implements AuctionRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addOrUpdateAuction(Auction a) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.saveOrUpdate(a);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public int countAuctionByIdProduct(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT COUNT(a) FROM Auction a WHERE a.idproduct.idproduct = :idproduct";
        Query q = s.createQuery(hql);
        q.setParameter("idproduct", id);

        return Integer.parseInt(q.getSingleResult().toString());

    }

    @Override
    public List<Auction> getAuctionsByIdUserProd(int idProduct, int idUser) {

        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Auction a WHERE a.iduser.iduser = :iduser AND a.idproduct.idproduct = :idproduct";
        Query q = s.createQuery(hql);
        q.setParameter("iduser", idUser);
        q.setParameter("idproduct", idProduct);
        return q.getResultList();
    }

    @Override
    public long getAuctionPriceMaxByIdProduct(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT MAX(a.price) FROM Auction a WHERE a.idproduct.idproduct = :idproduct";
        Query q = s.createQuery(hql);
        q.setParameter("idproduct", id);
        if (q.getSingleResult() != null) {
            return (long) q.getSingleResult();
        } else {
            return 0;
        }

    }

    @Override
    public List<Auction> getAuctionsByIdUser(int idUser) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Auction a WHERE a.iduser.iduser = :iduser";
        Query q = s.createQuery(hql);
        q.setParameter("iduser", idUser);
        return q.getResultList();
    }

    @Override
    public List<Auction> getAuctionsByIdPro(int idProduct) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Auction a WHERE a.idproduct.idproduct = :idproduct";
        Query q = s.createQuery(hql);
        q.setParameter("idproduct", idProduct);
        return q.getResultList();
    }

    @Override
    public Auction getAuctionById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Auction.class, id);
    }

    @Override
    public List<Auction> getAuctions() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Auction");
        return q.getResultList();
    }
}
