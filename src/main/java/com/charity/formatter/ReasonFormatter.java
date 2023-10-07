/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.formatter;

import com.charity.pojo.Reason;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ASUS
 */
public class ReasonFormatter implements Formatter<Reason> {

    @Override
    public String print(Reason reason, Locale locale) {
        return String.valueOf(reason.getIdreason());
    }

    @Override
    public Reason parse(String reasonId, Locale locale) throws ParseException {
        return new Reason(Integer.valueOf(reasonId));
    }

}
