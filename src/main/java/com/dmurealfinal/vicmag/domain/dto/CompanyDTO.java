package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Account;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Data
public class CompanyDTO {
    private String accountId;

    private Account account;

    private String companyRegistrationNumber;

    private String name;

    private String phone;

    private String email;
}
