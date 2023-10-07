/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.repository;

import com.charity.pojo.Auction;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface AuctionRepository {

    public boolean addOrUpdateAuction(Auction a);

    public List<Auction> getAuctionsByIdUserProd(int idProduct, int idUser);

    public List<Auction> getAuctionsByIdUser(int idUser);

    public List<Auction> getAuctionsByIdPro(int idProduct);
    
     public List<Auction> getAuctions();

    public Auction getAuctionById(int id);

    public int countAuctionByIdProduct(int id);

    public long getAuctionPriceMaxByIdProduct(int id);
}
