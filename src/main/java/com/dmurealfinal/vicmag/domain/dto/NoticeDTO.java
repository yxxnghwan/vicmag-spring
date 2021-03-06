package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.notice.Notice;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeDTO {
    private Long noticeSeq;

    private String title;

    private String contents;

    private String accountId;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedDateTime;

    public NoticeDTO() {}

    @Builder
    public NoticeDTO(Long noticeSeq, String title, String contents, String accountId, LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
        this.noticeSeq = noticeSeq;
        this.title = title;
        this.contents = contents;
        this.accountId = accountId;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

    public Notice toEntity() {
        return Notice.builder()
                .noticeSeq(this.noticeSeq)
                .title(this.title)
                .contents(this.contents)
                .accountId(this.accountId)
                .build();
    }
}
