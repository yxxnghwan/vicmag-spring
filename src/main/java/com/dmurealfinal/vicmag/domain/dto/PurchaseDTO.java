package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Company;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import com.dmurealfinal.vicmag.domain.entity.purchase.Payment;
import com.dmurealfinal.vicmag.domain.entity.purchase.SinglePurchase;
import com.dmurealfinal.vicmag.domain.entity.purchase.Subscribe;
import lombok.Data;

@Data
public class PurchaseDTO {
    private Long purchaseSeq;

    private String purchaseType;

    private User userId;

    private Company companyId;

    private SinglePurchase singlePurchase;

    private Subscribe subscribe;

    private Payment payment;
}
