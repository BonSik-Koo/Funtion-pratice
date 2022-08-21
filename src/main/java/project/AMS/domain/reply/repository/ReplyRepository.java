package project.AMS.domain.reply.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.AMS.domain.post.QPost;
import project.AMS.domain.reply.QReply;
import project.AMS.domain.reply.Reply;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final EntityManager em;
    //private final JPAQueryFactory query;

    public Reply add(Reply replyForm) {
        em.persist(replyForm);
        return replyForm;
    }

    public List<Reply> findALlReplyFromPost(Long postId) {
        JPAQueryFactory query = new JPAQueryFactory(em);

         return query.select(QReply.reply)
                .from(QReply.reply)
                .where(postIdEq(postId))
                .orderBy(QReply.reply.reGroup.asc(), QReply.reply.reLevel.asc(), QReply.reply.reDate.asc())
                .fetch();

    }
    private BooleanExpression postIdEq(Long postId) {
        if (postId == null)
            return null;
        return QReply.reply.post.id.eq(postId);
    }


}
