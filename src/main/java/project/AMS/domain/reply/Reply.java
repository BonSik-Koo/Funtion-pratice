package project.AMS.domain.reply;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import project.AMS.domain.post.Post;
import project.AMS.web.apiController.form.reply.ReplySaveForm;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post; //게시물 id

    private String userId; //댓글단 유저 id
    private String title;
    private String content;
    private LocalDateTime reDate; //댓글단 시간

    /**
     * 일단 나두고 그냥 댓글만 하는중...
     */
    //대댓글을 위한 변수
    private Long reGroup;
    private Long reLevel;


    /**
     * 생성 메서드
     */
    public void createReply(ReplySaveForm replySaveForm) {
        userId = replySaveForm.getUserId();
        content = replySaveForm.getContent();
        title = replySaveForm.getTitle();
        reDate = LocalDateTime.now();
    }

    /**
     * 양방향 연관관계 메서드
     */
    public void setPostReply(Post addPost) {
        post = addPost;
        addPost.getReply().add(this);
    }
}

