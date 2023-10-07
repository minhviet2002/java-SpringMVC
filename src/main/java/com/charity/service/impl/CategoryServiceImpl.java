/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.service.impl;

import com.charity.pojo.Category;
import com.charity.repository.CategoryRepository;
import com.charity.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.getCategory();
    }

    @Override
    public boolean addOrUpdateCategory(Category c) {
        if (!c.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(c.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                c.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.categoryRepository.addOrUpdateCategory(c);
    }

    @Override
    public Category getCategoryById(int id) {
        return this.categoryRepository.getCategoryById(id);
    }

    @Override
    public boolean deleteCateById(int id) {
        return this.categoryRepository.deleteCateById(id);
    }
    @Override
    public List<Object[]> countProductCate() {
        return this.categoryRepository.countProductCate();
    }

}
