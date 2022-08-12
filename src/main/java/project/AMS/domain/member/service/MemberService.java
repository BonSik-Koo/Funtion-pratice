package project.AMS.domain.member.service;

import project.AMS.domain.member.Member;

import java.util.Optional;

/**
 * 회원 회원가입, 아이디 찾기 , 수정과 관련된 메소드
 */
public interface MemberService {

    public Optional<Member> findByUserId(String userId);
    public void join(Member member);
    public void update(Member member);

}
