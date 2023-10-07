/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "reason")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reason.findAll", query = "SELECT r FROM Reason r"),
    @NamedQuery(name = "Reason.findByIdreason", query = "SELECT r FROM Reason r WHERE r.idreason = :idreason"),
    @NamedQuery(name = "Reason.findByName", query = "SELECT r FROM Reason r WHERE r.name = :name")})
public class Reason implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreason")
    private Integer idreason;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "idreason")
    private Set<ReasonReport> reasonReportSet;

    public Reason() {
    }

    public Reason(Integer idreason) {
        this.idreason = idreason;
    }

    public Integer getIdreason() {
        return idreason;
    }

    public void setIdreason(Integer idreason) {
        this.idreason = idreason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Set<ReasonReport> getReasonReportSet() {
        return reasonReportSet;
    }

    public void setReasonReportSet(Set<ReasonReport> reasonReportSet) {
        this.reasonReportSet = reasonReportSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreason != null ? idreason.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reason)) {
            return false;
        }
        Reason other = (Reason) object;
        if ((this.idreason == null && other.idreason != null) || (this.idreason != null && !this.idreason.equals(other.idreason))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.charity.pojo.Reason[ idreason=" + idreason + " ]";
    }
    
}
