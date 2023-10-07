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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "reason_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReasonReport.findAll", query = "SELECT r FROM ReasonReport r"),
    @NamedQuery(name = "ReasonReport.findByIdreasonReport", query = "SELECT r FROM ReasonReport r WHERE r.idreasonReport = :idreasonReport"),
    @NamedQuery(name = "ReasonReport.findByContent", query = "SELECT r FROM ReasonReport r WHERE r.content = :content"),
    @NamedQuery(name = "ReasonReport.findByCreatedDate", query = "SELECT r FROM ReasonReport r WHERE r.createdDate = :createdDate")})
public class ReasonReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreason_report")
    private Integer idreasonReport;
    @Size(max = 255)
    @Column(name = "content")
    private String content;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "idreason", referencedColumnName = "idreason")
    @ManyToOne
    private Reason idreason;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne
    private User iduser;
    @JoinColumn(name = "iduserreported", referencedColumnName = "iduser")
    @ManyToOne
    private User iduserreported;

    public ReasonReport() {
    }

    public ReasonReport(Integer idreasonReport) {
        this.idreasonReport = idreasonReport;
    }

    public Integer getIdreasonReport() {
        return idreasonReport;
    }

    public void setIdreasonReport(Integer idreasonReport) {
        this.idreasonReport = idreasonReport;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Reason getIdreason() {
        return idreason;
    }

    public void setIdreason(Reason idreason) {
        this.idreason = idreason;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public User getIduserreported() {
        return iduserreported;
    }

    public void setIduserreported(User iduserreported) {
        this.iduserreported = iduserreported;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreasonReport != null ? idreasonReport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReasonReport)) {
            return false;
        }
        ReasonReport other = (ReasonReport) object;
        if ((this.idreasonReport == null && other.idreasonReport != null) || (this.idreasonReport != null && !this.idreasonReport.equals(other.idreasonReport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.charity.pojo.ReasonReport[ idreasonReport=" + idreasonReport + " ]";
    }
    
}
