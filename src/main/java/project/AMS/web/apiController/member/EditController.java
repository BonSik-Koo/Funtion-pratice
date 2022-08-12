package project.AMS.web.apiController.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.service.MemberService;
import project.AMS.web.SessionConst;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class EditController {

    private final MemberService memberService ;

    @GetMapping("/edit")
    public String edit(@SessionAttribute(value = SessionConst.LOGIN_MEMBER , required = true) String member_id, Model model) {

        Member findMember = memberService.findByUserId(member_id)
                .orElse(null);
        model.addAttribute("member", findMember);

        return "member/edit/editForm";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Member member, BindingResult bindingResult) {

        memberService.update(member);
        log.info(member.getUserId() + "님의 회원정보 수정 완료");

        return "redirect:/";
    }
}
