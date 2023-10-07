/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.service;

import com.charity.pojo.Auction;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface AuctionService {

    public List<Auction> getAuctionsByIdPro(int idProduct);

    public int countAuctionByIdProduct(int id);

    public List<Auction> getAuctionsByIdUserProd(int idProduct, int idUser);

    public List<Auction> getAuctionsByIdUser(int idUser);

    public boolean addOrUpdateAuction(Auction a);

    public long getAuctionPriceMaxByIdProduct(int id);

    public Auction getAuctionById(int id);
    
    public List<Auction> getAuctions();
}
