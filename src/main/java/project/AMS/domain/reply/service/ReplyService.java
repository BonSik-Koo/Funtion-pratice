package project.AMS.domain.reply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.AMS.domain.post.Post;
import project.AMS.domain.post.repository.PostRepository;
import project.AMS.domain.reply.Reply;
import project.AMS.domain.reply.repository.ReplyRepository;
import project.AMS.web.apiController.form.reply.ReplySaveForm;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    @Transactional
    public Reply addReply(ReplySaveForm replySaveForm, Long postId) {

        //댓글단 게시물 찾기
        Post findPost = postRepository.findById(postId);

        //댓글 객체 생성
        Reply reply = new Reply();
        reply.createReply(replySaveForm);
        reply.setPostReply(findPost); //양방향 설정

        return replyRepository.add(reply); //저장
    }

    public List<Reply> findALLReplyLevel0(Integer postId) { //post에 댓글-댓글 모두 가져오기
        List<Reply> allReply = replyRepository.findALlReplyFromPost(postId);

        List<Reply> findReply = allReply.stream()
                .filter(r -> r.getReLevel() == 0L)
                .collect(Collectors.toList());
        return findReply;
    }

//    public List<ReplyForm> findAllReplyEqualGroup(Integer postId, Integer Group) { //post에 댓글-대댓글 모두 가져오기
//        List<ReplyForm> allReply = replyRepository.findALlReplyFromPost(postId);
//
//        allReply.stream()
//                .
//    }


}
