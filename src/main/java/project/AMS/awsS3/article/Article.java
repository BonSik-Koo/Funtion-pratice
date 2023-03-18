package project.AMS.awsS3.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setArticle(String title, String content){
        this.title = title;
        this.content = content;
    }
}
