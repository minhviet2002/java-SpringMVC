/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.service;

import com.charity.pojo.Product;
import com.charity.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface ProductService {

    public List<Product> getProducts(Map<String, String> params);

    public boolean updateProductActive(Product p);

    public boolean addOrUpdateProduct(Product p, User u);

    public Product getProductById(int id);

    public boolean deleteProductById(int id);

    public List<Product> getProductsByIdCate(int id, Map<String, String> params);

    public List<Product> getProductsByIdUser(int id);

    public int countProduct();

    public int countProductByIdCate(int id);

}
