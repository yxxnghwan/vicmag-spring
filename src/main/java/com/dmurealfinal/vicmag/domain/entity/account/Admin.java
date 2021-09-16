package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.dto.AdminDTO;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_ADMIN")
@Entity
public class Admin {
    @Id
    @Column(length = 30)
    private String accountId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @Column(length = 60)
    private String name;

    @Column(length = 60)
    private String department;

    @Column(length = 30)
    private String nickName;

    public Admin() {}

    @Builder
    public Admin(String accountId, String name, String department, String nickName) {
        this.accountId = accountId;
        this.name = name;
        this.department = department;
        this.nickName = nickName;
    }

    public AdminDTO toDTO() {
        return AdminDTO.builder()
                .accountId(this.accountId)
                .name(this.name)
                .department(this.department)
                .nickName(this.nickName)
                .build();
    }

    public static List<AdminDTO> toDTOList (List<Admin> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }
}
