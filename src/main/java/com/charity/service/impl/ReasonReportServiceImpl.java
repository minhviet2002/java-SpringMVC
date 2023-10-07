/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.service.impl;

import com.charity.pojo.Reason;
import com.charity.pojo.ReasonReport;
import com.charity.repository.ProductRepository;
import com.charity.repository.ReasonReportRepository;
import com.charity.repository.ReasonRepository;
import com.charity.service.ReasonReportService;
import com.charity.service.ReasonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ReasonReportServiceImpl implements ReasonReportService {

    @Autowired
    private ReasonReportRepository reasonReportRepository;

    @Override
    public boolean addReasonReport(ReasonReport r) {
        return this.reasonReportRepository.addReasonReport(r);
    }

    @Override
    public int countReportByIdProduct(int id) {
        return this.reasonReportRepository.countReportByIdProduct(id);
    }

    @Override
    public List<ReasonReport> getReasonReportByIdUser(int id) {
        return this.reasonReportRepository.getReasonReportByIdUser(id);
    }

}
