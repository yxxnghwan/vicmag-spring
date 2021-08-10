package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.dto.PaymentDTO;
import com.dmurealfinal.vicmag.domain.dto.SinglePurchaseDTO;
import com.dmurealfinal.vicmag.domain.dto.SubscribeDTO;
import com.dmurealfinal.vicmag.domain.entity.purchase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    SinglePurchaseRepository singlePurchaseRepository;
    @Autowired
    SubscribeRepository subscribeRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    /** 단건 구매 확인 */
    public boolean isSinglePurchased(String userId, Long magazineSeq) {
        int flag = purchaseRepository.isSinglePurchased(userId, magazineSeq);
        if(flag > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    /** 구독 구매 확인 */
    public boolean isSubscribed(String userId, Long magazineBoardSeq, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int flag = purchaseRepository.isSubscribed(userId, magazineBoardSeq, startDateTime, endDateTime);
        if(flag > 0) {
            return true;
        } else {
            return false;
        }
    }

    /** 단건 구매 등록 */
    @Transactional
    public void saveSinglePurchase(SinglePurchaseDTO singlePurchaseDTO) {
        Purchase purchase = singlePurchaseDTO.getPurchase().toEntity();
        purchase = purchaseRepository.save(purchase);

        purchaseRepository.flush();

        singlePurchaseDTO.setPurchaseSeq(purchase.getPurchaseSeq());

        SinglePurchase singlePurchase = singlePurchaseDTO.toEntity();
        singlePurchaseRepository.save(singlePurchase);

        PaymentDTO paymentDTO = singlePurchaseDTO.getPurchase().getPayment();
        paymentDTO.setPurchaseSeq(purchase.getPurchaseSeq());
        Payment payment = paymentDTO.toEntity();

        paymentRepository.save(payment);
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

        PaymentDTO paymentDTO = subscribeDTO.getPurchase().getPayment();
        paymentDTO.setPurchaseSeq(purchase.getPurchaseSeq());
        Payment payment = paymentDTO.toEntity();

        paymentRepository.save(payment);
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
