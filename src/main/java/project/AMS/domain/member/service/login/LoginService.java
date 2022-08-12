package project.AMS.domain.member.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String member_id, String member_password) {
        Member member = memberRepository.findByUserId(member_id)
                .filter(m -> m.getPassword().equals(member_password))
                .orElse(null); //일치하는 회원이 있을시 회원 반환 , 없을시 null반환
        return member;
    }
}
