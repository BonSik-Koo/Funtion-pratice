package project.AMS.domain.member;

import lombok.Data;
import project.AMS.domain.post.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity //table
public class Member {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickName;
    private String userId;
    private String password;
    private Integer birthday;

    @OneToMany(mappedBy = "member")
    private List<Post> post = new ArrayList<>();

    /**
     * 생성 메서드
     */
    public Member createMember(String nickName, String userId, String password, Integer birthday) {
        this.nickName = nickName;
        this.userId = userId;
        this.password = password;
        this.birthday = birthday;
        return this;
    }


}
