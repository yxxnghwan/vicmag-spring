package com.dmurealfinal.vicmag.domain.entity.purchase;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.PaymentDTO;
import com.dmurealfinal.vicmag.domain.entity.account.Account;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_PAYMENT")
@Entity
public class Payment {
    @Id
    private Long purchaseSeq;

    @OneToOne
    @JoinColumn(name = "purchaseSeq")
    private Purchase purchase;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(length = 300)
    private String description;

    @Column(length = 30)
    private String pg;

    @Column(length = 30, nullable = false)
    private String paymentType;

    public Payment() {}

    @Builder
    public Payment(Long purchaseSeq, Integer totalPrice, String description, String pg, String paymentType) {
        this.purchaseSeq = purchaseSeq;
        this.totalPrice = totalPrice;
        this.description = description;
        this.paymentType = paymentType;
        this.pg = pg;
    }

    public PaymentDTO toDTO() {
        return PaymentDTO.builder()
                .purchaseSeq(this.purchaseSeq)
                .totalPrice(this.totalPrice)
                .description(this.description)
                .pg(this.pg)
                .paymentType(this.paymentType)
                .build();
    }

    public static List<PaymentDTO> toDTOList (List<Payment> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }


}
