package com.dmurealfinal.vicmag.domain.entity.purchase;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "TB_SINGLE_PURCHASE")
@Entity
public class SinglePurchase {
    @Id
    private Long purchaseSeq;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Purchase purchase;

    @Column(columnDefinition = "double")
    private Double rating;

    private Long magazineSeq;

    public SinglePurchase() {}

    @Builder
    public SinglePurchase(Long purchaseSeq, Purchase purchase, Double rating, Long magazineSeq) {
        this.purchaseSeq = purchaseSeq;
        this.purchase = purchase;
        this.rating = rating;
        this.magazineSeq = magazineSeq;
    }
}
