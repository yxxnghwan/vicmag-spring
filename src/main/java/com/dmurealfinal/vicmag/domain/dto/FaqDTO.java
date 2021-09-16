package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.faq.Faq;
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
public class FaqDTO {
    private Long faqSeq;

    private String title;

    private String question;

    private String answer;

    private String accountId;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedDateTime;

    public FaqDTO() {}

    @Builder
    public FaqDTO(Long faqSeq, String title, String question, String answer, String accountId, LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
        this.faqSeq = faqSeq;
        this.title = title;
        this.question = question;
        this.answer = answer;
        this.accountId = accountId;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

    public Faq toEntity() {
        return Faq.builder()
                .faqSeq(this.faqSeq)
                .title(this.title)
                .question(this.question)
                .answer(this.answer)
                .accountId(this.accountId)
                .build();
    }
}
