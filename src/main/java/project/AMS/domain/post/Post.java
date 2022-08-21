package project.AMS.domain.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.AMS.domain.hashtag.posthashtag.PostHashtag;
import project.AMS.domain.member.Member;
import project.AMS.domain.reply.Reply;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id; //게시물 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //작성자 id

    private String title; //제목
    private String problem_uri; //문제 링크
    private String content; //작성한  내용

    private LocalDateTime redate; //등록 날짜
    private LocalDateTime chdate; //마지막 수정 날짜

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Reply> reply = new ArrayList<>();



    /**
     * 태그 부분
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostHashtag> postHashtags = new ArrayList<>();
    /**
     * 양방향 연관관계 메서드
     */
    public void addPostHashtag(PostHashtag postHashtag) {
        postHashtag.setPost(this);
        postHashtags.add(postHashtag);
    }




    //생성 메서드
    public Post createPost(Member member, String title, String problem_uri, String content) {
        this.member = member;
        this.title = title;
        this.problem_uri = problem_uri;
        this.content = content;
        this.redate = LocalDateTime.now();
        this.chdate = null;

        return this;
    }

//    양방향 연관관계 메소드 -> reply 에 있는게 좋은지 어떤게 좋지??!
//    public void setReply(Reply rep){
//        reply.add(rep);
//        rep.setPost(this);
//    }

}
