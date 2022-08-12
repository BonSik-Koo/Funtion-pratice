package project.AMS.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.AMS.domain.member.Member;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) String member_id,
                            Model model) {

        //로그인 하지 않은 사용자
        if(member_id==null) {
            return "home";
        }

        model.addAttribute("member_id", member_id);
        return "loginHome";
    }
}
