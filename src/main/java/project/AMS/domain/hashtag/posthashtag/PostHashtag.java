package project.AMS.domain.hashtag.posthashtag;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.AMS.domain.hashtag.hashtag.Hashtag;
import project.AMS.domain.post.Post;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PostHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * 생성 메서드
     */
    public static PostHashtag createPostHashtag(Hashtag hashtag) {
        PostHashtag postHashtag = new PostHashtag();
        postHashtag.setHashtag(hashtag);

        return postHashtag;
    }
}
