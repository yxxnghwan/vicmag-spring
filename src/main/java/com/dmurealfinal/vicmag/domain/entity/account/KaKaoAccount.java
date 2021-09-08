package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.dto.KakaoAccountDTO;
import lombok.Builder;
import lombok.Getter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "TB_KAKAO_ACCOUNT")
@Entity
public class KaKaoAccount {
    @Id
    @Column(length = 30)
    private String accountId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @Column(unique = true)
    private Long kakaoIdNumber;

    @Column LocalDateTime connectedAt;

    public KaKaoAccount() {}

    @Builder
    public KaKaoAccount(String accountId, Long kakaoIdNumber, LocalDateTime connectedAt) {
        this.accountId = accountId;
        this.kakaoIdNumber = kakaoIdNumber;
        this.connectedAt = connectedAt;
    }

    public KakaoAccountDTO toDTO() {
        return KakaoAccountDTO.builder()
                .accountId(this.accountId)
                .kakaoIdNumber(this.kakaoIdNumber)
                .connectedAt(this.connectedAt)
                .build();
    }

    public static List<KakaoAccountDTO> toDTOList(List<KaKaoAccount> entityList) {
        List<KakaoAccountDTO> result = new ArrayList<>();
        for(KaKaoAccount entity : entityList) {
            result.add(entity.toDTO());
        }

        return result;
    }
}
