package project.AMS.domain.reply.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.AMS.domain.reply.Reply;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final EntityManager em;

    public Reply add(Reply replyForm) {
        em.persist(replyForm);
        return replyForm;
    }

    public List<Reply> findALlReplyFromPost(Integer postId) {
        String sql = "select r from reply r where r.post_id=:id";
        List<Reply> findReply = em.createQuery(sql, Reply.class)
                .setParameter("id", postId)
                .getResultList();
        return findReply;
    }

//    public List<ReplyForm> findALLReplyLevel0(Long postId) { //post에 level이 0인 모든 댓글들-루트 댓글들
//        String sql = "select r from reply r where r.post_id=:id and r.reLevel= 0";
//        List<ReplyForm> findPosts = em.createQuery(sql, ReplyForm.class)
//                .setParameter("id", postId)
//                .getResultList();
//
//        return findPosts;
//    }
//
//    @Query(value = "select r from Reply r where r.post_id=:id , r.reGroup=:group")
//    public List<ReplyForm> findAllReplyEqualGroup(@Param(value = "id") Long postId, @Param(value = "group") int group){}


}
