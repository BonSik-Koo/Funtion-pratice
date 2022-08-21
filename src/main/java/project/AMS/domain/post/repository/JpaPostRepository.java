package project.AMS.domain.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import project.AMS.domain.member.QMember;
import project.AMS.domain.post.Post;
import project.AMS.domain.post.QPost;
import project.AMS.domain.post.search.PostSearch;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JpaPostRepository implements PostRepository{

    private final EntityManager em;

    @Override
    public Post write(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public void edit(Long postId, Post post) {
        Post editForm = em.find(Post.class, postId);
        editForm.setTitle(post.getTitle());
        editForm.setChdate(post.getChdate());
    }

    @Override
    public void delete(Post post) {
        em.remove(post);
    }

    @Override
    public List<Post> findAll() {
        String sql = "select p from Post p";
        List<Post> findAll = em.createQuery(sql, Post.class)
                .getResultList();
        return  findAll;
    }

    /**
     * 추후 "페이징" 기능 넣기, 해시태그 기능
     */
    @Override
    public List<Post> findAllByUserId(String userId) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        return query.select(QPost.post)
                .from(QPost.post)
                .join(QPost.post.member, QMember.member)
                .where(userIdEq(userId))
                .orderBy(QPost.post.redate.desc())
                .fetch();
    }
    private BooleanExpression userIdEq(String userId) {
        if(!StringUtils.hasText(userId))
            return null;

        return QPost.post.member.userId.eq(userId);

    }


    public Post findByPostId(Long postId) {
        Post findPost = em.find(Post.class, postId);
        return findPost;
    }



}
