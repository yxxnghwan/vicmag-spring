package com.dmurealfinal.vicmag.domain.entity.purchase;

import com.dmurealfinal.vicmag.domain.dto.PurchaseDTO;
import com.dmurealfinal.vicmag.domain.dto.SinglePurchaseDTO;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_SINGLE_PURCHASE")
@Entity
public class SinglePurchase {
    @Id
    private Long purchaseSeq;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Purchase purchase;

    @Column(columnDefinition = "double")
    private Double rating;

    private Long magazineSeq;

    public SinglePurchase() {}

    @Builder
    public SinglePurchase(Long purchaseSeq, Double rating, Long magazineSeq) {
        this.purchaseSeq = purchaseSeq;
        this.rating = rating;
        this.magazineSeq = magazineSeq;
    }

    public SinglePurchaseDTO toDTO() {
        return SinglePurchaseDTO.builder()
                .purchaseSeq(this.purchaseSeq)
                .rating(this.rating)
                .magazineSeq(this.magazineSeq)
                .build();
    }

    public static List<SinglePurchaseDTO> toDTOList (List<SinglePurchase> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }
}
