/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.service.impl;

import com.charity.pojo.Auction;
import com.charity.repository.AuctionRepository;
import com.charity.service.AuctionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Override
    public boolean addOrUpdateAuction(Auction a) {
        return this.auctionRepository.addOrUpdateAuction(a);
    }

    @Override
    public int countAuctionByIdProduct(int id) {
        return this.auctionRepository.countAuctionByIdProduct(id);
    }

    @Override
    public List<Auction> getAuctionsByIdUserProd(int idProduct, int idUser) {
        return this.auctionRepository.getAuctionsByIdUserProd(idProduct, idUser);
    }

    @Override
    public long getAuctionPriceMaxByIdProduct(int id) {
        return this.auctionRepository.getAuctionPriceMaxByIdProduct(id);
    }

    @Override
    public List<Auction> getAuctionsByIdUser(int idUser) {
        return this.auctionRepository.getAuctionsByIdUser(idUser);
    }

    @Override
    public List<Auction> getAuctionsByIdPro(int idProduct) {
        return this.auctionRepository.getAuctionsByIdPro(idProduct);
    }

    @Override
    public Auction getAuctionById(int id) {
        return this.auctionRepository.getAuctionById(id);
    }

    @Override
    public List<Auction> getAuctions() {
        return this.auctionRepository.getAuctions();
    }

}
