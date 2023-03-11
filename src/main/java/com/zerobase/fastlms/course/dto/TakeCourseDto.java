package com.zerobase.fastlms.course.dto;

import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Data
public class TakeCourseDto {
    Long id;

    long courseId;
    String userId;

    long payPrice; // 결제 금액 (할인된 금액을 넣어서 진행)
    String status; // 상태 (수강신청, 결제완료, 수강취소)


    LocalDateTime regDt; // 신청일

    // join
    String userName;
    String phone;
    String subject;

    // 추가컬럼
    long totalCount;
    long seq;

    public String getRegDtText() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return regDt != null ? regDt.format(dateTimeFormatter) : "";

    }
}
