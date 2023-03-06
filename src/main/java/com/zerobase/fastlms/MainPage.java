package com.zerobase.fastlms;

// MainPage 라는 클래스를 만든 목적 ?
// 매핑하기 위해서.
// 어떤 거?
// 주소와(논리적인 주소. 인터넷 주소) 물리적인 파일 ( 프로그래밍을 해야 하니까 ) 매핑

// https://naver.com/new/list.do
// 하나의 주소에 대해서
// 어디서 매핑? 누가 매핑?
// 후보.. 3 가지
//  클래스, 속성과 메서드로 이루어짐.

// http://localhost:8080/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 주소를 매핑하는 특정한 클래스가 컨트롤러이다. Controller 가 아닌 RestController 를 쓴 이유는 나중에..
public class MainPage {

    @RequestMapping("/")
    public String index() {
        return "Index Page";
    }

    @RequestMapping("/hello")
    public String hello() {

        String msg = "hello \r\n fast lms website";

        return msg;
    }

}
