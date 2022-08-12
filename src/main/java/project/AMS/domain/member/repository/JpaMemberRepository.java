package project.AMS.domain.member.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.AMS.domain.member.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member saveMember(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    //@Modifying(clearAutomatically = true)
    //@Transactional
    //@Query(value = "update Member m set m.member_name=:name , m.member_birth=:birth where m.member_id=:id")
    public void updateMember(String name,Integer birth,String id){
        String sql = "select m from Member m where m.userId =:id";
        //String sql1 = "update Member m set m.member_name=:name and m.member_birth =:birth where m.member_id=:id";

        Member findMember = em.createQuery(sql, Member.class)
                .setParameter("id", id)
                .getSingleResult();

        findMember.setNickName(name);
        findMember.setBirthday(birth);
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        String sql = "select m from Member m where m.userId=:id";
        List<Member> result = em.createQuery(sql, Member.class)
                .setParameter("id", userId)
                .getResultList();

        return result.stream().findAny(); //찾은 첫번째 회원을 반환
    }

}
