package com.dmurealfinal.vicmag.domain.entity.purchase;

import com.dmurealfinal.vicmag.domain.dto.SubscribeDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_SUBSCRIBE")
@Entity
public class Subscribe {
    @Id
    private Long purchaseSeq;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Purchase purchase;


    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    @Column(columnDefinition = "double")
    private Double rating;

    @Column
    private Long magazineBoardSeq;

    public Subscribe() {}

    @Builder
    public Subscribe(Long purchaseSeq, LocalDateTime startDateTime, LocalDateTime endDateTime, Double rating, Long magazineBoardSeq) {
        this.purchaseSeq = purchaseSeq;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.rating = rating;
        this.magazineBoardSeq = magazineBoardSeq;
    }

    public SubscribeDTO toDTO() {
        return SubscribeDTO.builder()
                .purchaseSeq(this.purchaseSeq)
                .startDateTime(this.startDateTime)
                .endDateTime(this.endDateTime)
                .rating(this.rating)
                .magazineBoardSeq(this.magazineBoardSeq)
                .build();
    }

    public static List<SubscribeDTO> toDTOList (List<Subscribe> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }
}
