package com.dmurealfinal.vicmag.domain.entity.notice;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import com.dmurealfinal.vicmag.domain.dto.NoticeDTO;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_NOTICE")
@Entity
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeSeq;

    @Column(length = 100)
    private String title;

    @Column(length = 1500)
    private String contents;

    public Notice() {}

    @Builder
    public Notice(Long noticeSeq, String title, String contents) {
        this.noticeSeq = noticeSeq;
        this.title = title;
        this.contents = contents;
    }

    public NoticeDTO toDTO() {
        return NoticeDTO.builder()
                .noticeSeq(this.noticeSeq)
                .title(this.title)
                .contents(this.contents)
                .createdDateTime(this.createdDateTime)
                .modifiedDateTime(this.modifiedDateTime)
                .build();
    }

    public static List<NoticeDTO> toDTOList(List<Notice> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }

}
