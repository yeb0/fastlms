package com.zerobase.fastlms.admin.mapper;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface LoginHistoryMapper {

    long logCntNumByUserId(@Param("userId") String userId); //


    List<LoginHistoryDto> logHistoryByUserId(@Param("userId") String userId,
                                             @Param("pageStart") long pageStart,
                                             @Param("pageSize") long pageSize );// 다수 parameter 변수에 @param 붙일 것


}
