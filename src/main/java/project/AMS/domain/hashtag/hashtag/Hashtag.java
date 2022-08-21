package project.AMS.domain.hashtag.hashtag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    private String name;

    public static Hashtag createHashtag(String name) {
        Hashtag hashtag = new Hashtag();
        hashtag.name = name;
        return hashtag;
    }
}
