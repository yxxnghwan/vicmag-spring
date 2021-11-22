package com.dmurealfinal.vicmag.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReadPermissionDTO {
    private List<SinglePurchaseDTO> purchasedMagazines;

    private Boolean isSubscribed;
}
