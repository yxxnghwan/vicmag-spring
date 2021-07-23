package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.dto.SinglePurchaseDTO;
import com.dmurealfinal.vicmag.domain.entity.purchase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    SinglePurchaseRepository singlePurchaseRepository;
    @Autowired
    SubscribeRepository subscribeRepository;

    /** 단건 구매 등록 */
    @Transactional
    public void saveSinglePurchase(SinglePurchaseDTO singlePurchaseDTO) {
        Purchase purchase = singlePurchaseDTO.getPurchase().toEntity();
        purchase = purchaseRepository.save(purchase);

        purchaseRepository.flush();

        singlePurchaseDTO.setPurchaseSeq(purchase.getPurchaseSeq());

        SinglePurchase singlePurchase = singlePurchaseDTO.toEntity();
        singlePurchaseRepository.save(singlePurchase);
    }
}
