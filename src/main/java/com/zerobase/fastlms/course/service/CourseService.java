package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;

import java.util.List;

public interface CourseService {
    /**
     * 강의 등록
     */
    boolean add(CourseInput parameter);

    /**
     * 강의목록
     */
    List<CourseDto> list(CourseParam parameter);

    /**
     *강의 상세 정보
     */
    CourseDto getById(long id);

    /**
     * 강의 정보 수정
     */
    boolean set(CourseInput parameter);

    /**
     * 강의 내용 삭제
     */
    boolean del(String idList);

    /**
     * 프론트 강의 목록
     */
    List<CourseDto> frontList(CourseParam parameter); // 강좌에 대해서 관리자가 잠깐 이 내용에 대해서 판매를 중지하거나.. -> 같을 순 없다 list


    /**
     * 프론트 강좌 상세 정보
     */
    CourseDto frontDetail(long id);

    /**
     * 수강신청
     */
    ServiceResult req(TakeCourseInput parameter);

    /**
     * 전체 강의 목록
     */
    List<CourseDto> listAll();
}
