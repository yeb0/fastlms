package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class MemberInput {

    private String userId;
    private String userName;
    private String password;
    private String phone;

}
