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

import com.zerobase.fastlms.Utils;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.service.impl.BannerService;
import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@RestController // 주소를 매핑하는 특정한 클래스가 컨트롤러이다. Controller 가 아닌 RestController 를 쓴 이유는 나중에..
@RequiredArgsConstructor
@Controller // Controller 는 그대로 return이 불가능.. RestController 라면 return "hello"; 이런 것을 그대로 뿜어낼 수 있지만, Controller는 그렇게 하려면 서블릿을 이용할 것(일단 예제에선 ㅇㅇ)
public class MainController {

    private final MailComponents mailComponents;
    private final BannerService bannerService;

    /**
     *메인 페이지
     */

    /**
     * @RequestMapping 과 @GetMapping
     * @GetMapping을 쓰고 로그인에 실패했을 때 화면에 실패했다고 띄워져야했지만
     * 띄워지지 않아 RequestMapping 으로 바꿔서 실행해본 결과 잘 나왔음.
     *
     * ++++
     *
     * @GetMapping 도 잘 나옴. 무슨 문제였는지 이해 불가..
     * http.formLogin()
     *                 .loginPage("/member/login")
     *                 .usernameParameter("username")
     *                 .passwordParameter("password")
     *        point    .failureHandler(getFailureHandler()) // 로그인에 실패했을 때.
     *        point    .successHandler(getSuccessHandler()) // 로그인에 성공했을 때
     *                 .permitAll();
     *
     * 시큐리티의 순서를 실패부터 바꿔봤는데 이 과정때문인지는 모르겠음.
     *
     * ++++
     * @RequestMapping은 클래스 레벨과 메소드 둘다 맵핑 할 수 있고,
     * @GetMapping은 메소드에만 맵핑 할 수 있다.
     * 그러니까 @GetMapping이 더 세분화되었다고 볼수있다. REF) https://salty-computer-until-night.tistory.com/57
     *
     * @GetMapping 안에 @RequestMapping 이 있음. <- 둘 중 뭘 쓰는지는 팀바팀이라 함
     */
//    @RequestMapping("/")
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
//        String userAgent = RequestUtils.getUserAgent(request);
//        String clientIp = RequestUtils.getClientIp(request);

//        String agent = request.getHeader("User-Agent"); // agent
//        System.out.println(agent);
//
//        System.out.println("###########");
//        String userIp = Utils.getClientIp(request);
//        System.out.println(userIp);

//        log.info(userAgent);
//        log.info(clientIp);
        /**
         * 배너 가져오기 dto 에 실어서 갖고 올 거임
         */
        List<BannerDto> list = bannerService.list();
        model.addAttribute("list", list);

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


    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }

}
