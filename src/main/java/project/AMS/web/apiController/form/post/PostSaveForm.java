package project.AMS.web.apiController.form.post;

import lombok.Data;

@Data
public class PostSaveForm {

    private String problem_uri; //문제 uri
    private String title; //제목
    private String content; //코드
}
