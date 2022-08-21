//package project.AMS.domain.reply.service;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//import project.AMS.domain.member.Member;
//import project.AMS.domain.member.service.MemberService;
//import project.AMS.domain.post.Post;
//import project.AMS.domain.post.service.PostService;
//import project.AMS.domain.reply.Reply;
//import project.AMS.web.apiController.form.reply.ReplySaveForm;
//
//import javax.persistence.EntityManager;
//
//@SpringBootTest
//@Transactional
//class ReplyServiceTest {
//
//    @Autowired
//    private ReplyService replyService;
//    @Autowired
//    private MemberService memberService;
//    @Autowired
//    private PostService postService;
//
//    @Autowired
//    private EntityManager em;
//
//
//    @BeforeEach
//    public void before() {
//        Member member = new Member();
//        member.createMember("1", "1", "1", 1);
//        memberService.join(member);
//
//        Post post1 = new Post();
//        post1.createPost(member, "123","123","123");
//        postService.regPost(post1);
//
//        ReplySaveForm replySaveForm1 = new ReplySaveForm("jerry1", "test1", "test1", 1,1);
//        ReplySaveForm replySaveForm2 = new ReplySaveForm("jerry2", "test2", "test2", 1,2);
//        ReplySaveForm replySaveForm3 = new ReplySaveForm("jerry3", "test3", "test3", 2,1);
//        ReplySaveForm replySaveForm4 = new ReplySaveForm("jerry4", "test4", "test4", 1,2);
//        ReplySaveForm replySaveForm5 = new ReplySaveForm("jerry5", "test5", "test5", 2,2);
//
//        replyService.addReply(replySaveForm1, 1L);
//        replyService.addReply(replySaveForm2, 1L);
//        replyService.addReply(replySaveForm3, 1L);
//        replyService.addReply(replySaveForm4, 1L);
//        replyService.addReply(replySaveForm5, 1L);
//
//        System.out.println("=========================================");
//    }
//
//    @Test
//    @Rollback(value = false)
//    public void 댓글저장테스트() throws Exception {
//
//        //given
//        Post findPost = postService.findPostByPostId(1L);
//        ReplySaveForm replySaveForm = new ReplySaveForm("jerry", "test", "test",0,0);
//
//        //when
//        replyService.addReply(replySaveForm, findPost.getId());
//
//        //then
//        Post post = postService.findPostByPostId(1L);
//        Assertions.assertThat(post.getReply().size()).isEqualTo(1);
//
//    }
//
//    @Test
//    @Rollback(value = false)
//    public void 댓글조회테스트() throws Exception {
//        replyService.findAllReply(1L);
//    }
//
//}