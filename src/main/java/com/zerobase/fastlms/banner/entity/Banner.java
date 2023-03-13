package com.zerobase.fastlms.banner.entity;

import com.zerobase.fastlms.banner.model.BannerInput;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Banner {
    @Id
    @GeneratedValue
    Long id; // pk로 지정
    long bannerId;

    String name; // 명
    String imageUrl; // 배너파일 url
    String linkUrl; // 링크 주소
    String openType; // 오픈 방법
    int sortValue; // 정렬 순서
    boolean usingYn; // 공개여부 (= 사용여부인듯)
    LocalDate regDt; // 등록일 ( 관리에서 사용할 것 )


}
