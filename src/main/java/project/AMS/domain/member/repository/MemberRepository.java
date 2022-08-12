package project.AMS.domain.member.repository;

import project.AMS.domain.member.Member;

import java.util.Optional;

public interface MemberRepository {
    public Member saveMember(Member member);
    public void updateMember(String name, Integer birth, String id); //회원 이름, 생년월일만 변경 가능
    public Optional<Member> findByUserId(String member_id);
    //public Optional<Member> findByMemberPassword(String member_password);
}
