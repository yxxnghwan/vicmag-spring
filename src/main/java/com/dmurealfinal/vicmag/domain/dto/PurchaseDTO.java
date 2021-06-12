package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Company;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import com.dmurealfinal.vicmag.domain.entity.purchase.Payment;
import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import com.dmurealfinal.vicmag.domain.entity.purchase.SinglePurchase;
import com.dmurealfinal.vicmag.domain.entity.purchase.Subscribe;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
public class PurchaseDTO {
    private Long purchaseSeq;

    private String purchaseType;

    private String userId;

    private String companyId;


    // for response
    private UserDTO user;

    private CompanyDTO company;

    private SinglePurchaseDTO singlePurchase;

    private SubscribeDTO subscribe;

    private PaymentDTO payment;

    public PurchaseDTO() {}

    @Builder
    public PurchaseDTO(Long purchaseSeq, String purchaseType, String userId, String companyId, UserDTO user, CompanyDTO company, SinglePurchaseDTO singlePurchase, SubscribeDTO subscribe, PaymentDTO payment) {
        this.purchaseSeq = purchaseSeq;
        this.purchaseType = purchaseType;
        this.userId = userId;
        this.companyId = companyId;
        this.user = user;
        this.company = company;
        this.singlePurchase = singlePurchase;
        this.subscribe = subscribe;
        this.payment = payment;
    }

    public Purchase toEntity() {
        return Purchase.builder()
                .purchaseSeq(this.purchaseSeq)
                .purchaseType(this.purchaseType)
                .userId(this.userId)
                .companyId(this.companyId)
                .build();
    }
}
