package project.AMS.web.apiController.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.service.MemberService;
import project.AMS.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String join(@ModelAttribute Member member) {return "member/join";}

    @PostMapping("/join")
    public String join(@ModelAttribute Member member , BindingResult bindingResult) {

        try {
            memberService.join(member);
            return "redirect:/";
        }
        catch (IllegalStateException e ) { //중복 아이디 일 경우
            bindingResult.rejectValue("member_id", "duplicate", "중복된 아이디 입니다.");
            return "member/join";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session!=null) {
            session.removeAttribute(SessionConst.LOGIN_MEMBER);
        }
        return "redirect:/";
    }
}
