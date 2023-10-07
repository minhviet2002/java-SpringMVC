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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "comment_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommentPost.findAll", query = "SELECT c FROM CommentPost c"),
    @NamedQuery(name = "CommentPost.findByIdcomment", query = "SELECT c FROM CommentPost c WHERE c.idcomment = :idcomment"),
    @NamedQuery(name = "CommentPost.findByComment", query = "SELECT c FROM CommentPost c WHERE c.comment = :comment"),
    @NamedQuery(name = "CommentPost.findByCreatedDate", query = "SELECT c FROM CommentPost c WHERE c.createdDate = :createdDate")})
public class CommentPost implements Serializable {

    @Lob()
    @Size(max = 2147483647)
    @Column(name = "comment")
    private String comment;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomment")
    private Integer idcomment;
    @JoinColumn(name = "idpost", referencedColumnName = "idpost")
    @ManyToOne(optional = false)
    private Post idpost;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User iduser;

    public CommentPost() {
    }

    public CommentPost(Integer idcomment) {
        this.idcomment = idcomment;
    }

    public CommentPost(Integer idcomment, String comment, Date createdDate) {
        this.idcomment = idcomment;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public Integer getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(Integer idcomment) {
        this.idcomment = idcomment;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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
        hash += (idcomment != null ? idcomment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentPost)) {
            return false;
        }
        CommentPost other = (CommentPost) object;
        if ((this.idcomment == null && other.idcomment != null) || (this.idcomment != null && !this.idcomment.equals(other.idcomment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.charity.pojo.CommentPost[ idcomment=" + idcomment + " ]";
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
