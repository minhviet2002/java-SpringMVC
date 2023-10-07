/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.service;

import com.charity.pojo.ReasonReport;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ReasonReportService {

    public boolean addReasonReport(ReasonReport r);

    public int countReportByIdProduct(int id);

    public List<ReasonReport> getReasonReportByIdUser(int id);
}
