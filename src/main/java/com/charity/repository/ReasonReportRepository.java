/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.repository;

import com.charity.pojo.Product;
import com.charity.pojo.Reason;
import com.charity.pojo.ReasonReport;
import com.charity.pojo.User;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ReasonReportRepository {

    public boolean addReasonReport(ReasonReport r);

    public int countReportByIdProduct(int id);

    public List<ReasonReport> getReasonReportByIdUser(int id);

}
