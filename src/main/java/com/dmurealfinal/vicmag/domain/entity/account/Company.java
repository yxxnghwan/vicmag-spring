package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.CompanyDTO;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_COMPANY")
@Entity
public class Company{
    @Id
    @Column(length = 30)
    private String accountId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @Column(length = 100, unique = true, nullable = false)
    private String companyRegistrationNumber;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(length = 30)
    private String phone;

    @Column(length = 150)
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

    public static List<CompanyDTO> toDTOList (List<Company> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }
}
