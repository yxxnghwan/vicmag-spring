package com.dmurealfinal.vicmag.domain.entity.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}