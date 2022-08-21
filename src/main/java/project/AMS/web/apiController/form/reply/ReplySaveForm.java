package project.AMS.web.apiController.form.reply;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplySaveForm {

    public String userId; //댓글단 사용자 id -> 여기서 받아올수 있낭?!

    public String title;
    public String content;

    public Integer reGroup; //댓글-대댓글 집합 표시 (1 ~ ++)
    public Integer reLevel;  //댓글(1)인지 대댓글(2)인지 판별 (1,2로 구분)

}
