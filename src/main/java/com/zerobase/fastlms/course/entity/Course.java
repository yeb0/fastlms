package com.zerobase.fastlms.course.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long categoryId;

    String imagePath;
    String keyword;
    String subject;

    @Column(length = 1000)
    String summary;

    @Lob // text 사이즈가 엄청 길어질 수 있기에.. (내용 넣는 거라서) (Lob라는 어노테이션을 이용하여 길이를 늘림 (?))
    String contents;

    long price;
    long salePrice;
    LocalDate saleEndDt; // 일자만 확인하려고 LocalDate 타입으로 받음

    LocalDateTime regDt; // 등록일 (추가)
    LocalDateTime udtDt; // 수정일 (수정날짜)
}
