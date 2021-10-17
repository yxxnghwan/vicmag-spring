package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Account;
import com.dmurealfinal.vicmag.domain.entity.account.Company;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Data
public class CompanyDTO {
    private String accountId;

    private String companyRegistrationNumber;

    private String name;

    private String phone;

    private String email;

    // for response
    private AccountDTO account;

    private KakaoAccountDTO kakaoAccount;

    public CompanyDTO() {}

    @Builder
    public CompanyDTO(String accountId, String companyRegistrationNumber, String name, String phone, String email, AccountDTO account, KakaoAccountDTO kakaoAccount) {
        this.accountId = accountId;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.account = account;
        this.kakaoAccount = kakaoAccount;
    }

    public Company toEntity() {
        return Company.builder()
                .accountId(this.accountId)
                .companyRegistrationNumber(this.companyRegistrationNumber)
                .name(this.name)
                .phone(this.phone)
                .email(this.email)
                .build();
    }
}
