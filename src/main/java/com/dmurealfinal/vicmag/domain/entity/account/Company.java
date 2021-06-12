package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.dto.CompanyDTO;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "TB_COMPANY")
@Entity
public class Company {
    @Id
    @Column(columnDefinition = "nvarchar(30)")
    private String accountId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @Column(columnDefinition = "nvarchar(100)", unique = true, nullable = false)
    private String companyRegistrationNumber;

    @Column(columnDefinition = "nvarchar(30)", nullable = false)
    private String name;

    @Column(columnDefinition = "nvarchar(15)")
    private String phone;

    @Column(columnDefinition = "nvarchar(100)")
    private String email;

    public Company() {}

    @Builder
    public Company(String accountId, String companyRegistrationNumber, String name, String phone, String email) {
        this.accountId = accountId;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public CompanyDTO toDTO() {
        return CompanyDTO.builder()
                .accountId(this.accountId)
                .companyRegistrationNumber(this.companyRegistrationNumber)
                .name(this.name)
                .phone(this.phone)
                .email(this.email)
                .build();
    }
}
