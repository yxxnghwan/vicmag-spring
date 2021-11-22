package com.dmurealfinal.vicmag.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReadPermissionDTO {
    private List<Long> purchasedMagazineSeqs;

    private Boolean isSubscribed;

    public ReadPermissionDTO() {}

    public ReadPermissionDTO(List<Long> purchasedMagazineSeqs, Boolean isSubscribed) {
        this.purchasedMagazineSeqs = purchasedMagazineSeqs;
        this.isSubscribed = isSubscribed;
    }
}
