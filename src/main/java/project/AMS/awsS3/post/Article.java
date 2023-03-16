package project.AMS.awsS3.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.AMS.awsS3.image.Image;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String context;

    public Article(String title, String context) {
        this.title = title;
        this.context = context;
    }
}
