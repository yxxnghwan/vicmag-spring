package com.dmurealfinal.vicmag.domain.dto.kakao;

import lombok.Builder;
import lombok.Data;

@Data
public class KakaoAuthCodeReceiveDTO {
    private String code;
    private String state;
    private String error;
    private String error_description;

    public KakaoAuthCodeReceiveDTO() {}

    @Builder
    public KakaoAuthCodeReceiveDTO(String code, String state, String error, String error_description) {
        this.code = code;
        this.state = state;
        this.error = error;
        this.error_description = error_description;
    }

}
