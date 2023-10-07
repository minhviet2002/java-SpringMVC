/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.service;

import com.charity.pojo.Category;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface CategoryService {

    List<Category> getCategories();

    public boolean addOrUpdateCategory(Category c);

    public Category getCategoryById(int id);

    public boolean deleteCateById(int id);
    
    public List<Object[]> countProductCate();
}
