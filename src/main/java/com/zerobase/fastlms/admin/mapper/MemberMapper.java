package com.zerobase.fastlms.admin.mapper;


import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<MemberDto> selectList(MemberDto parameter);

    List<LoginHistoryDto> selectList2(LoginHistoryDto parameter);

}
