package project.AMS.web.apiController.form.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAndHashtagForm {

    private String problem_uri; //문제 uri
    private String title; //제목
    private String content; //코드

    /**
     * 해시 태그가 여러개니 => 저장에 대해서는 service까지 구현 하였음, 조회에 대해서는 아직 해시태그 한개로 필터링 되게 함
     * form 방식은 리스트를 못받으니 무조건 json 형태로만??!
     */
    private List<String> hashtag = new ArrayList<>();
}
