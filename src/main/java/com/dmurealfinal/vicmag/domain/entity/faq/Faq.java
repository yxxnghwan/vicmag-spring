package com.dmurealfinal.vicmag.domain.entity.faq;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import com.dmurealfinal.vicmag.domain.dto.FaqDTO;
import com.dmurealfinal.vicmag.domain.dto.NoticeDTO;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_FAQ")
@Entity
public class Faq extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faqSeq;

    @Column(length = 100)
    private String title;

    @Column(length = 1000)
    private String question;

    @Column(length = 1500)
    private String answer;

    @Column(length = 30)
    private String accountId;

    public Faq() {}

    @Builder
    public Faq(Long faqSeq, String title, String question, String answer, String accountId) {
        this.faqSeq = faqSeq;
        this.title = title;
        this.question = question;
        this.answer = answer;
        this.accountId = accountId;
    }

    public FaqDTO toDTO() {
        return FaqDTO.builder()
                .faqSeq(this.faqSeq)
                .faqSeq(this.faqSeq)
                .title(this.title)
                .question(this.question)
                .answer(this.answer)
                .accountId(this.accountId)
                .createdDateTime(this.createdDateTime)
                .modifiedDateTime(this.modifiedDateTime)
                .build();
    }

    public static List<FaqDTO> toDTOList(List<Faq> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }

}
