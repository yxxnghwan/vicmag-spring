package com.dmurealfinal.vicmag.domain.entity.utils;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "TB_CATEGORY")
@Entity
public class Category {

    @Id
    @Column(length = 20)
    private String category;

    public Category() {}

    public Category(String category) {
        this.category = category;
    }
}
