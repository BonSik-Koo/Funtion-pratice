package project.AMS.awsS3.image;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.AMS.awsS3.post.Article;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id @GeneratedValue
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;


    public Image(String imageUrl, Article article) {
        this.imageUrl = imageUrl;
        this.article = article;
    }
}
