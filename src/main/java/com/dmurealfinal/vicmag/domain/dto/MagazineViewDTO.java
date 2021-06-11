package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.User;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class MagazineViewDTO {
    private Long magazineViewSeq;

    private User user;

    private Magazine magazine;
}
