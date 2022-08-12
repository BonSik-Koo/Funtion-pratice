package project.AMS.web.apiController.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.service.login.LoginService;
import project.AMS.web.SessionConst;
import project.AMS.web.apiController.form.member.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm) {return "member/login/loginForm";}

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        Member loginMember = loginService.login(loginForm.getMember_id(), loginForm.getMember_password());

        //아이디 or 패스워드가 일치하지 않는 경우
        if(loginMember==null) {
            bindingResult.reject("loginError", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            return "member/login/loginForm";
        }

        //정상적으로 로그인이 된 경우 - 세션 생성
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember.getUserId());

        return "redirect:" + redirectURL;
    }

}
