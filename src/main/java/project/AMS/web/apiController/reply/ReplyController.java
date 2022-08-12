package project.AMS.web.apiController.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.AMS.domain.post.Post;
import project.AMS.domain.post.service.PostService;
import project.AMS.domain.reply.Reply;
import project.AMS.domain.reply.repository.ReplyRepository;
import project.AMS.domain.reply.service.ReplyService;
import project.AMS.web.apiController.form.reply.ReplySaveForm;
import project.AMS.web.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 댓글 저장 api
     * ??????댓글을 단 회원아이디를 어트케 받아오나????????????????
     * 1. JWT 토큰을 통해서 아는 방법?
     * 2. 쿠키를 담아주는법?? 이건 위험하지않나 사용자 정보니?? 아이디정도는 괜찮나?? 닉네임 정도??!
     * 3. 프론트엔드가 브라우저 저장소에서 가지고 있다가 주는 법??이건 아닌데..움움
     *
     * 일단은 프론트엔드에게 받아오는걸로 해놨음
    */
    @PostMapping("/{userId}/{postId}/reply") //여기서 "userId"는 게시물 주인의 사용자 아이디!!
    public SuccessResponse addReply(@PathVariable("userId") String userId, @PathVariable("postId")Long postId,
                           @ModelAttribute ReplySaveForm replySaveForm) {


        //정상 흐름
        Reply reply = replyService.addReply(replySaveForm, postId);
        SuccessResponse response = new SuccessResponse<>("200", "댓글 저장 성공입니다.", null);
        return response;
    }


}
