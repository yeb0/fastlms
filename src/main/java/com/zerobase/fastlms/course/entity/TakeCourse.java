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
public class TakeCourse implements TakeCourseCode {

    /* impl TakeCourseCode
    public static String STATUS_REQ = "REQ"; // 수강신청
    public static String STATUS_COMPLETE = "COMPLETE"; // 결제완료
    public static String STATUS_CANCEL = "CANCEL"; // 수강취소
     */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long courseId;
    String userId;

    long payPrice; // 결제 금액 (할인된 금액을 넣어서 진행)
    String status; // 상태 (수강신청, 결제완료, 수강취소)


    LocalDateTime regDt; // 신청일
}
