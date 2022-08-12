package project.AMS.domain.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    @Override
    public void join(Member member) {
        validDuplicateMember(member);
        memberRepository.saveMember(member);
    }
    //중복 아이디 검사
    public void validDuplicateMember (Member member) {
        memberRepository.findByUserId(member.getUserId())
                .ifPresent(e -> {
                    throw new IllegalStateException("이미 존재하는 아이디 입니다.");
                });
    }

    @Override
    public void update(Member member) {
        memberRepository.updateMember(member.getNickName(), member.getBirthday(), member.getUserId());
    }
}
