/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.service.impl;

import com.charity.pojo.Product;
import com.charity.pojo.User;
import com.charity.repository.ProductRepository;
import com.charity.service.ProductService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public boolean addOrUpdateProduct(Product p, User u) {
        if (!p.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(p.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                p.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.productRepository.addOrUpdateProduct(p, u);
    }

    @Override
    public Product getProductById(int id) {
        return this.productRepository.getProductById(id);
    }

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        return this.productRepository.getProducts(params);
    }

    @Override
    public boolean deleteProductById(int id) {
        return this.productRepository.deleteProductById(id);
    }

    @Override
    public List<Product> getProductsByIdCate(int id, Map<String, String> params) {
        return this.productRepository.getProductsByIdCate(id, params);
    }

    @Override
    public List<Product> getProductsByIdUser(int id) {
        return this.productRepository.getProductsByIdUser(id);
    }

    @Override
    public boolean updateProductActive(Product p) {
        return this.productRepository.updateProductActive(p);
    }

    @Override
    public int countProduct() {
        return this.productRepository.countProduct();
    }

    @Override
    public int countProductByIdCate(int id) {
        return this.productRepository.countProductByIdCate(id);
    }

}
