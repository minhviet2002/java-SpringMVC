/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByIdproduct", query = "SELECT p FROM Product p WHERE p.idproduct = :idproduct"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByStartprice", query = "SELECT p FROM Product p WHERE p.startprice = :startprice"),
    @NamedQuery(name = "Product.findByEndprice", query = "SELECT p FROM Product p WHERE p.endprice = :endprice"),
    @NamedQuery(name = "Product.findByCreatedDate", query = "SELECT p FROM Product p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Product.findByImage", query = "SELECT p FROM Product p WHERE p.image = :image"),
    @NamedQuery(name = "Product.findByActive", query = "SELECT p FROM Product p WHERE p.active = :active"),
    @NamedQuery(name = "Product.findByEndDate", query = "SELECT p FROM Product p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Product.findByStatus", query = "SELECT p FROM Product p WHERE p.status = :status")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduct")
    private Integer idproduct;
    @Basic(optional = false)
    @NotNull(message = "Không được bỏ trống trường")
    @Size(min = 1, max = 45, message = "Tên sản phẩm phải từ 1 - 45 kí tự")
    @Column(name = "name")
    private String name;
    @NotNull(message = "Không được bỏ trống trường")
    @Size(max = 255, message = "Mô tả tối đa 255 kí tự")
    @Column(name = "description")
    private String description;
    @NotNull(message = "Không được bỏ trống trường")
    @Column(name = "startprice")
    private Long startprice;
    @Column(name = "endprice")
    private Long endprice;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "category_id", referencedColumnName = "idcategory")
    @ManyToOne(optional = false)
    private Category categoryId;
    @JoinColumn(name = "user_created", referencedColumnName = "iduser")
    @ManyToOne
    private User userCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduct")
    private Set<SaleOrder> saleOrderSet;
    @OneToMany(mappedBy = "idproduct")
    private Set<Auction> auctionSet;

    @Transient
    private MultipartFile file;

    @Transient
    private String date;

    public Product() {
    }

    public Product(Integer idproduct) {
        this.idproduct = idproduct;
    }

    public Product(Integer idproduct, String name) {
        this.idproduct = idproduct;
        this.name = name;
    }

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStartprice() {
        return startprice;
    }

    public void setStartprice(Long startprice) {
        this.startprice = startprice;
    }

    public Long getEndprice() {
        return endprice;
    }

    public void setEndprice(Long endprice) {
        this.endprice = endprice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    @XmlTransient
    public Set<SaleOrder> getSaleOrderSet() {
        return saleOrderSet;
    }

    public void setSaleOrderSet(Set<SaleOrder> saleOrderSet) {
        this.saleOrderSet = saleOrderSet;
    }

    @XmlTransient
    public Set<Auction> getAuctionSet() {
        return auctionSet;
    }

    public void setAuctionSet(Set<Auction> auctionSet) {
        this.auctionSet = auctionSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduct != null ? idproduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.idproduct == null && other.idproduct != null) || (this.idproduct != null && !this.idproduct.equals(other.idproduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.charity.pojo.Product[ idproduct=" + idproduct + " ]";
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

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

}
