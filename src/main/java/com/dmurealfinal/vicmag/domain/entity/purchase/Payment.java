package com.dmurealfinal.vicmag.domain.entity.purchase;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "TB_PAYMENT")
@Entity
public class Payment {
    @Id
    private Long purchaseSeq;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaseSeq")
    private Purchase purchase;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(columnDefinition = "nvarchar(150)")
    private String description;

    @Column(columnDefinition = "nvarchar(20)", nullable = false)
    private String paymentType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "datetime default now()")
    private LocalDateTime payDateTime;

    public Payment() {}

    @Builder
    public Payment(Long purchaseSeq, Integer totalPrice, String description, String paymentType, LocalDateTime payDateTime) {
        this.purchaseSeq = purchaseSeq;
        this.totalPrice = totalPrice;
        this.description = description;
        this.paymentType = paymentType;
        this.payDateTime = payDateTime;
    }


}
