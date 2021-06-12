package com.dmurealfinal.vicmag.domain.entity.purchase;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import com.dmurealfinal.vicmag.domain.dto.PurchaseDTO;
import com.dmurealfinal.vicmag.domain.entity.account.Company;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "TB_PURCHASE")
@Entity
public class Purchase extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseSeq;

    @Column(columnDefinition = "nvarchar(20)", nullable = false)
    private String purchaseType;

    @Column(columnDefinition = "nvarchar(30)")
    private String userId;

    @Column(columnDefinition = "nvarchar(30)")
    private String companyId;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.REMOVE)
    private SinglePurchase singlePurchase;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.REMOVE)
    private Subscribe subscribe;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.REMOVE)
    private Payment payment;

    public Purchase() {}

    @Builder
    public Purchase(Long purchaseSeq, String purchaseType, String userId, String companyId, SinglePurchase singlePurchase, Subscribe subscribe, Payment payment) {
        this.purchaseSeq = purchaseSeq;
        this.purchaseType = purchaseType;
        this.userId = userId;
        this.companyId = companyId;
        this.singlePurchase = singlePurchase;
        this.subscribe = subscribe;
        this.payment = payment;
    }

    public PurchaseDTO toDTO() {
        return PurchaseDTO.builder()
                .purchaseType(this.purchaseType)
                .userId(this.userId)
                .companyId(this.companyId)
                .build();
    }
}
