package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.dto.SinglePurchaseDTO;
import com.dmurealfinal.vicmag.domain.dto.SubscribeDTO;
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

    /** 구독 구매 등록 */
    @Transactional
    public void saveSubscribe(SubscribeDTO subscribeDTO) {
        Purchase purchase = subscribeDTO.getPurchase().toEntity();
        purchase = purchaseRepository.save(purchase);

        purchaseRepository.flush();

        subscribeDTO.setPurchaseSeq(purchase.getPurchaseSeq());

        Subscribe subscribe = subscribeDTO.toEntity();
        subscribeRepository.save(subscribe);
    }

    /** 단건 구매 삭제 */
    @Transactional
    public void deleteSinglePurchase(Long purchaseSeq) {
        singlePurchaseRepository.deleteById(purchaseSeq);
        purchaseRepository.deleteById(purchaseSeq);
    }

    /** 구독 구매 삭제 */
    @Transactional
    public void deleteSubscribe(Long purchaseSeq) {
        subscribeRepository.deleteById(purchaseSeq);
        purchaseRepository.deleteById(purchaseSeq);
    }
}
