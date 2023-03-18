package project.AMS.awsS3.image;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.AMS.awsS3.article.Article;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id @GeneratedValue
    private Long id;
    private String originalImageName;
    private String storageImageName;
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;


    public Image(String originalImageName, String storageImageName, String imagePath, Article article) {
        this.originalImageName = originalImageName;
        this.storageImageName = storageImageName;
        this.imagePath = imagePath;
        this.article = article;
    }
}
