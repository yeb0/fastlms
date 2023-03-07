package com.zerobase.fastlms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity // table 을 뜻한다. 그리고 repository 를 이용해 저장을 한다.
@Builder // 값을 좀 더 찾기 편하게 하기 위함 ... Builder 패턴을 위한 @NoArgsConstructor, @AllArgsConstructor <- 세트라고 보면 됨.
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 객체 생성자
@Data // getter, setter 에 대한 data
public class Member {

    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;
    private LocalDateTime regDt;

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;
}
