/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.service.impl;

import com.charity.pojo.Reason;
import com.charity.repository.ProductRepository;
import com.charity.repository.ReasonRepository;
import com.charity.service.ReasonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ReasonServiceImpl implements ReasonService {

    @Autowired
    private ReasonRepository reasonRepository;

    @Override
    public List<Reason> getReasons() {
        return this.reasonRepository.getReasons();
    }

}
