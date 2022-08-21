package project.AMS.web.Dto.post;

import lombok.Data;
import project.AMS.domain.post.Post;
import project.AMS.domain.reply.Reply;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostDto {

    private Long id; // post 고유 ID

    private String title;
    private String problem_uri;
    private String content;
    private LocalDateTime redate; //작성한 날짜

    private List<ReplyDto> replys = new ArrayList<>(); //댓글들 -> 하나의 댓글에 달린 대댓글을 모아야되는데.....움...

//    public PostDto(Post post) {
//        id = post.getId();
//        title = post.getTitle();
//        problem_uri = post.getProblem_uri();
//        content = post.getContent();
//        redate = post.getRedate();
//    }

    /**
     * 댓글, 대댓글 그룹 파싱
     */
    public PostDto(Post post, List<Reply> replies) {

        id = post.getId();
        title = post.getTitle();
        problem_uri = post.getProblem_uri();
        content = post.getContent();
        redate = post.getRedate();

        boolean checkReply = false;
        boolean checkRReply = false;
        ReplyDto replyDto = null;
        for(int i=0;i<replies.size();i++) {
            Reply reply = replies.get(i);

            if(reply.getReLevel() == 1) { //댓글
                if(checkRReply == true) {
                    checkRReply = false;
                    this.replys.add(replyDto);
                }
                else if(checkReply == true) { //댓글 다음에 댓글인 상황
                    this.replys.add(replyDto);
                }
                replyDto = new ReplyDto(reply);
                checkReply = true;
            }
            else { //대댓글
                checkRReply = true; //대댓글 존재 여부
                checkReply = false;
                replyDto.rreplys.add(new RReplyDto(reply));

            }

            if(i==replies.size()-1) { //마지막이 경우
                this.replys.add(replyDto);
            }
        }
    }

    /**
     * 나중에 여기 "댓글", "태크" 도 같이 보내준다.
     */
    @Data
    static class ReplyDto {
        private String userId;
        private String title;
        private String content;
        private LocalDateTime reDate; //댓글 시간

        private List<RReplyDto> rreplys = new ArrayList<>();

        public ReplyDto(Reply reply) {
            userId = reply.getUserId();
            title = reply.getTitle();
            content = reply.getContent();
            reDate = reply.getReDate();
        }
    }

    @Data
    static class RReplyDto {
        private String userId;
        private String content;
        private LocalDateTime reDate; //댓글 시간

        public RReplyDto(Reply reply) {
            this.userId = reply.getUserId();
            this.content = reply.getContent();
            this.reDate = reply.getReDate();
        }
    }

}
