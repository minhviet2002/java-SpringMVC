/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.pojo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "like_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LikePost.findAll", query = "SELECT l FROM LikePost l"),
    @NamedQuery(name = "LikePost.findByIdlike", query = "SELECT l FROM LikePost l WHERE l.idlike = :idlike"),
    @NamedQuery(name = "LikePost.findByCreatedDate", query = "SELECT l FROM LikePost l WHERE l.createdDate = :createdDate")})
public class LikePost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlike")
    private Integer idlike;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "created_date")
    private String createdDate;
    @JoinColumn(name = "idpost", referencedColumnName = "idpost")
    @ManyToOne(optional = false)
    private Post idpost;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User iduser;

    public LikePost() {
    }

    public LikePost(Integer idlike) {
        this.idlike = idlike;
    }

    public LikePost(Integer idlike, String createdDate) {
        this.idlike = idlike;
        this.createdDate = createdDate;
    }

    public Integer getIdlike() {
        return idlike;
    }

    public void setIdlike(Integer idlike) {
        this.idlike = idlike;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Post getIdpost() {
        return idpost;
    }

    public void setIdpost(Post idpost) {
        this.idpost = idpost;
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
        hash += (idlike != null ? idlike.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LikePost)) {
            return false;
        }
        LikePost other = (LikePost) object;
        if ((this.idlike == null && other.idlike != null) || (this.idlike != null && !this.idlike.equals(other.idlike))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.charity.pojo.LikePost[ idlike=" + idlike + " ]";
    }
    
}
