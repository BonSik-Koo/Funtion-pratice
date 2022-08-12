package project.AMS.web.Dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.AMS.domain.post.Post;

@Data
@AllArgsConstructor

public class PostListDto {

    private Long id; //문제 id
    private String title; //문제 제목

    public PostListDto(Post o) {
        id = o.getId();
        title = o.getTitle();
    }
}
