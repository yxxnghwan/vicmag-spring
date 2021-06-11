package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long purchaseSeq;

    private Purchase purchase;

    private Integer totalPrice;

    private String description;

    private String paymentType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payDateTime;
}
