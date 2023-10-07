/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "auction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a"),
    @NamedQuery(name = "Auction.findByIdauction", query = "SELECT a FROM Auction a WHERE a.idauction = :idauction"),
    @NamedQuery(name = "Auction.findByPrice", query = "SELECT a FROM Auction a WHERE a.price = :price"),
    @NamedQuery(name = "Auction.findByActive", query = "SELECT a FROM Auction a WHERE a.active = :active"),
    @NamedQuery(name = "Auction.findByCreatedDate", query = "SELECT a FROM Auction a WHERE a.createdDate = :createdDate")})
public class Auction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idauction")
    private Integer idauction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private long price;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct")
    @ManyToOne
    private Product idproduct;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne
    private User iduser;

    public Auction() {
    }

    public Auction(Integer idauction) {
        this.idauction = idauction;
    }

    public Auction(Integer idauction, long price) {
        this.idauction = idauction;
        this.price = price;
    }

    public Integer getIdauction() {
        return idauction;
    }

    public void setIdauction(Integer idauction) {
        this.idauction = idauction;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Product idproduct) {
        this.idproduct = idproduct;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idauction != null ? idauction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auction)) {
            return false;
        }
        Auction other = (Auction) object;
        if ((this.idauction == null && other.idauction != null) || (this.idauction != null && !this.idauction.equals(other.idauction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.charity.pojo.Auction[ idauction=" + idauction + " ]";
    }
    
}
