package project.AMS;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.AMS.domain.hashtag.hashtag.Hashtag;
import project.AMS.domain.hashtag.hashtag.HashtagRepository;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.service.MemberService;
import project.AMS.domain.post.Post;
import project.AMS.domain.post.service.PostService;
import project.AMS.domain.reply.service.ReplyService;
import project.AMS.web.apiController.form.post.PostAndHashtagForm;
import project.AMS.web.apiController.form.reply.ReplySaveForm;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class initDB {

    private final initService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class initService {

        private final MemberService memberService;
        private final PostService postService;
        private final ReplyService replyService;
        private final HashtagRepository hashtagRepository;

        public void dbInit1() {
            //회원 저장
            Member member = new Member();
            member.createMember("1", "1", "1", 1);
            memberService.join(member);

            //게시물 저장
            Post post1 = new Post();
            post1.createPost(member, "123","123","123");
            postService.regPost(post1);


            ReplySaveForm replySaveForm1 = new ReplySaveForm("jerry1", "test1", "test1", 1,1);
            ReplySaveForm replySaveForm2 = new ReplySaveForm("jerry2", "test2", "test2", 2,1);
            ReplySaveForm replySaveForm3 = new ReplySaveForm("jerry3", "test3", "test3", 3,1);
            ReplySaveForm replySaveForm4 = new ReplySaveForm("jerry4", "test4", "test4", 2,2);
            ReplySaveForm replySaveForm5 = new ReplySaveForm("jerry5", "test5", "test5", 5,1);

            // 댓글 저장
            replyService.addReply(replySaveForm1, 1L);
            replyService.addReply(replySaveForm2, 1L);
            replyService.addReply(replySaveForm3, 1L);
            replyService.addReply(replySaveForm4, 1L);
            replyService.addReply(replySaveForm5, 1L);

//            //해시태그 저장
//            Hashtag tag1 = Hashtag.createHashtag("구본식");
//            Hashtag tag2 = Hashtag.createHashtag("양소은");
//            hashtagRepository.save(tag1);
//            hashtagRepository.save(tag2);
//
//            List<String> hash = new ArrayList<>();
//            hash.add("구본식"); hash.add("양소은");
//            PostAndHashtagForm postAndHashtagForm = new PostAndHashtagForm("test1", "test1", "test1",hash );
//
//
//            Long postId = postService.regPostAndHashTag(postAndHashtagForm, "1");
        }

    }

}
