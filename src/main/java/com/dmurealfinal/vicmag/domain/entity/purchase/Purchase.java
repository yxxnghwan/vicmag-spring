package com.dmurealfinal.vicmag.domain.entity.purchase;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import com.dmurealfinal.vicmag.domain.dto.PurchaseDTO;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_PURCHASE")
@Entity
public class Purchase extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseSeq;

    @Column(length = 30, nullable = false)
    private String purchaseType;

    @Column(length = 30)
    private String userId;

    @Column(length = 30)
    private String companyId;

    @Column(length = 50)
    private String merchantUid;

    @Column(length = 50)
    private String impUid;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.REMOVE)
    private SinglePurchase singlePurchase;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.REMOVE)
    private Subscribe subscribe;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.REMOVE)
    private Payment payment;

    public Purchase() {}

    @Builder
    public Purchase(Long purchaseSeq, String purchaseType, String userId, String companyId, String merchantUid,String impUid, SinglePurchase singlePurchase, Subscribe subscribe, Payment payment, LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
        this.purchaseSeq = purchaseSeq;
        this.purchaseType = purchaseType;
        this.userId = userId;
        this.companyId = companyId;
        this.merchantUid = merchantUid;
        this.impUid = impUid;
        this.singlePurchase = singlePurchase;
        this.subscribe = subscribe;
        this.payment = payment;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

    public PurchaseDTO toDTO() {
        return PurchaseDTO.builder()
                .purchaseType(this.purchaseType)
                .userId(this.userId)
                .companyId(this.companyId)
                .merchantUid(this.merchantUid)
                .impUid(this.impUid)
                .createdDateTime(this.createdDateTime)
                .modifiedDateTime(this.modifiedDateTime)
                .build();
    }

    public static List<PurchaseDTO> toDTOList (List<Purchase> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }
}
