package com.zerobase.fastlms.main.controller;

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

import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@RestController // 주소를 매핑하는 특정한 클래스가 컨트롤러이다. Controller 가 아닌 RestController 를 쓴 이유는 나중에..
@RequiredArgsConstructor
@Controller // Controller 는 그대로 return이 불가능.. RestController 라면 return "hello"; 이런 것을 그대로 뿜어낼 수 있지만, Controller는 그렇게 하려면 서블릿을 이용할 것(일단 예제에선 ㅇㅇ)
public class MainController {

    private final MailComponents mailComponents;

    /**
     *메인 페이지
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * class에 Controller를 했을 때 해당 메서드가 오류가 나는 이유 ?
     * maven기준, pom 파일 가보면..
     *
     * <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-thymeleaf</artifactId>
     *</dependency>
     * 를 의존했는데, 타임리프를 쓰는 것은.. 우리가 예상했던 값은
     * http://localhost:8080/
     * 을 들어가면 index 라는 텍스트가 찍혀야 하는데 안 찍힌다. 그 이유는? 타임리프가 return "index";를
     * index라는 파일을 찾아서 리턴해야 하는데 그런 파일이 없기에 오류를 뿜는 것.
     */
//    @RequestMapping("/")
//    public String index() {
//        return "index";
//    }


    // Spring -> MVC (View -> 템플릿 엔진 화면에 출력 (html))
    // .NET -> MVC (View -> 출력)
    // python django -> MTV( Template -> 화면 출력 )
    // 백엔드 과정 -> view 까진.. 안 감 -> 프론트엔드가 함.
    // 어쨌든 백엔드도 당연 웹이기에 알긴 해야함. V -> HTML -> 웹페이지
    // V -> json -> api (백엔드)


    // request -> web -> server

    // response -> server -> web
    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        String msg = "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body>" +
                "<p>hello</p> <p>fastlms website</p>" +
                "<p> 안녕하세요 ! </p>" +
                "</body>" +
                "</html>";

        writer.write(msg);
        writer.close();

    }

}
