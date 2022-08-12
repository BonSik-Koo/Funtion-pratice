package project.AMS.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 713736192L;

    public static final QMember member = new QMember("member1");

    public final NumberPath<Integer> birthday = createNumber("birthday", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final ListPath<project.AMS.domain.post.Post, project.AMS.domain.post.QPost> post = this.<project.AMS.domain.post.Post, project.AMS.domain.post.QPost>createList("post", project.AMS.domain.post.Post.class, project.AMS.domain.post.QPost.class, PathInits.DIRECT2);

    public final StringPath userId = createString("userId");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

