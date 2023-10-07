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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findByIdpost", query = "SELECT p FROM Post p WHERE p.idpost = :idpost"),
    @NamedQuery(name = "Post.findByHastag", query = "SELECT p FROM Post p WHERE p.hastag = :hastag"),
    @NamedQuery(name = "Post.findByCreatedDate", query = "SELECT p FROM Post p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Post.findByImage", query = "SELECT p FROM Post p WHERE p.image = :image")})
public class Post implements Serializable {

    @Basic(optional = false)
    @NotNull(message = "Không bỏ trống trường")
    @Lob()
    @Size(min = 1, max = 2147483647)
    @Column(name = "content")
    private String content;
    @Size(min = 1, max = 45, message = "Không bỏ trống trường")
    @Column(name = "hastag")
    private String hastag;
    @Size(max = 100)
    @Column(name = "image")
    private String image;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpost")
    private Integer idpost;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne
    private User iduser;
    
    @Transient
    private MultipartFile file;

    public Post() {
    }

    public Post(Integer idpost) {
        this.idpost = idpost;
    }

    public Post(Integer idpost, String content) {
        this.idpost = idpost;
        this.content = content;
    }

    public Integer getIdpost() {
        return idpost;
    }

    public void setIdpost(Integer idpost) {
        this.idpost = idpost;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
        hash += (idpost != null ? idpost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.idpost == null && other.idpost != null) || (this.idpost != null && !this.idpost.equals(other.idpost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.charity.pojo.Post[ idpost=" + idpost + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHastag() {
        return hastag;
    }

    public void setHastag(String hastag) {
        this.hastag = hastag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
