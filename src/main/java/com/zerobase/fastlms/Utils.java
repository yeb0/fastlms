package com.zerobase.fastlms;


import javax.servlet.http.HttpServletRequest;

/**
 * RequestUtils 가 지원이 안 되는 건지(Spring version 이슈???) 그래서 따로 Utils 클래스를 만들어 IP, userAgent 를 뽑으려 했으나
 * 구글링을 통해 request.getHeader("User-Agent"), request.getRemoteAddr(); 를 통해서 IP와 userAgent 를 뽑아낼 수 있다는 것을
 * 알아내서 이용했습니다.
 *
 * 하지만 이 상태로 IP와 userAgent 를 찍어보면 IP의 값이 제대로 나오지 않습니다. ex) 0:0:0:0:0:0:0:1(IPv6)
 * 원하는 값은 127.0.0.1(IPv4)...
 *
 * 이는 공통된 규약 주소이며 루프백 주소(Loopback Address)라 한다.
 *
 * IPv6은 IPv4의 주소 공간 한계로 할당할 수 있는 IP공간을 늘린 주소 체계를 가진 인터넷 프로토콜을 의미한다.
 * IPv4는 32비트의 주소 공간을 사용하며, IPv6은 128비트의 주소 공간을 사용함.
 *
 * 그래서 원하는 값으로 프론트에 보여지게 하려면 VM 설정에 따로 추가를 해줘야만 한다.
 * Edit Configurations 에 들어가서 VM Option 에 -Djava.net.preferIPv4Stack=true를 적어주면 해결할 수 있다. (왜 그런건지는...)
 *
 * +++
 * tomcat으로 따로 서버를 설정해 들어갔더니 다시 IPv6 주소로 나왔는데, 이도 역시 tomcat 도 위와 같은 방법으로 설정해주면 다시
 * IPv4 로 볼 수 있다.
 *
 * IPv4 IPv6 Reference --> https://ooz.co.kr/138
 */

public class Utils {
    public static String getClientIp(HttpServletRequest req) { // ip 가져오기
        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null) ip = req.getRemoteAddr();
        return ip;
    }

    public static String getUserAgent(HttpServletRequest req) {
        String agent = req.getHeader("User-Agent");
        return agent;
    }
}
