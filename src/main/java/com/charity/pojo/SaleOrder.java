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
@Table(name = "sale_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaleOrder.findAll", query = "SELECT s FROM SaleOrder s"),
    @NamedQuery(name = "SaleOrder.findByIdsaleOrder", query = "SELECT s FROM SaleOrder s WHERE s.idsaleOrder = :idsaleOrder"),
    @NamedQuery(name = "SaleOrder.findByPaid", query = "SELECT s FROM SaleOrder s WHERE s.paid = :paid"),
    @NamedQuery(name = "SaleOrder.findByUnitPrice", query = "SELECT s FROM SaleOrder s WHERE s.unitPrice = :unitPrice"),
    @NamedQuery(name = "SaleOrder.findByCreatedDate", query = "SELECT s FROM SaleOrder s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "SaleOrder.findByDayPayment", query = "SELECT s FROM SaleOrder s WHERE s.dayPayment = :dayPayment")})
public class SaleOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsale_order")
    private Integer idsaleOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paid")
    private boolean paid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unit_price")
    private long unitPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "day_payment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dayPayment;
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct")
    @ManyToOne(optional = false)
    private Product idproduct;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User iduser;

    public SaleOrder() {
    }

    public SaleOrder(Integer idsaleOrder) {
        this.idsaleOrder = idsaleOrder;
    }

    public SaleOrder(Integer idsaleOrder, boolean paid, long unitPrice, Date createdDate) {
        this.idsaleOrder = idsaleOrder;
        this.paid = paid;
        this.unitPrice = unitPrice;
        this.createdDate = createdDate;
    }

    public Integer getIdsaleOrder() {
        return idsaleOrder;
    }

    public void setIdsaleOrder(Integer idsaleOrder) {
        this.idsaleOrder = idsaleOrder;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDayPayment() {
        return dayPayment;
    }

    public void setDayPayment(Date dayPayment) {
        this.dayPayment = dayPayment;
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
        hash += (idsaleOrder != null ? idsaleOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleOrder)) {
            return false;
        }
        SaleOrder other = (SaleOrder) object;
        if ((this.idsaleOrder == null && other.idsaleOrder != null) || (this.idsaleOrder != null && !this.idsaleOrder.equals(other.idsaleOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.charity.pojo.SaleOrder[ idsaleOrder=" + idsaleOrder + " ]";
    }
    
}
