package project.AMS.web.Dto.post;

import lombok.Data;
import project.AMS.domain.post.Post;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private Long id; // post 고유 ID
    private String title;
    private String problem_uri;
    private String content;
    private LocalDateTime redate; //작성한 날짜

    public PostDto(Post post) {
        id = post.getId();
        title = post.getTitle();
        problem_uri = post.getProblem_uri();
        content = post.getContent();
        redate = post.getRedate();
    }

    /**
     * 나중에 여기 "댓글", "태크" 도 같이 보내준다.
     */
}
