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

    @Column(length = 150, unique = true, nullable = false)
    private String kakaoEmail;

    @Column(length = 200)
    private String accessToken;

    @Column (length = 200)
    private String refreshToken;

    @Column
    private LocalDateTime expiresIn;

    @Column
    private LocalDateTime refreshTokenExpiresIn;

    public KaKaoAccount() {}

    @Builder
    public KaKaoAccount(String accountId, String kakaoEmail, String accessToken, String refreshToken, LocalDateTime expiresIn, LocalDateTime refreshTokenExpiresIn) {
        this.accountId = accountId;
        this.kakaoEmail = kakaoEmail;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public KakaoAccountDTO toDTO() {
        return KakaoAccountDTO.builder()
                .accountId(this.accountId)
                .kakaoEmail(this.kakaoEmail)
                .accessToken(this.accessToken)
                .refreshToken(this.refreshToken)
                .expiresIn(this.expiresIn)
                .refreshTokenExpiresIn(this.refreshTokenExpiresIn)
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
