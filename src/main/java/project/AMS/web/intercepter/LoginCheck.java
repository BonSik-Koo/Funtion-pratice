package project.AMS.web.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import project.AMS.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheck implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("로그인 체크 인터셉터 실행 {} ",requestURI);

        HttpSession session = request.getSession();
        if(session==null || session.getAttribute(SessionConst.LOGIN_MEMBER) ==null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/member/login?redirectURL=" + requestURI);
            return false; //핸들러 어댑터를 호출하지 않음
        }
        return true; //다음 인터셉터나 핸들러 어댑터 호출
    }
}
