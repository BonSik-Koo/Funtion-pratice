package project.AMS.web.apiController.form.reply;

import lombok.Data;

@Data
public class ReplySaveForm {

    public String userId; //댓글단 사용자 id
    public String title;
    public String content;

    public ReplySaveForm(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
