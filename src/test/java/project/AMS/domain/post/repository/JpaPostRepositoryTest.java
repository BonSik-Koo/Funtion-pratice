package project.AMS.domain.post.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.service.MemberService;
import project.AMS.domain.post.Post;
import project.AMS.domain.post.service.PostService;

import java.util.List;



@SpringBootTest
@Transactional
class JpaPostRepositoryTest {
   
   @Autowired
   private MemberService memberService;
   
   @Autowired private PostService postService;
   
   @BeforeEach
   public void 회원가입() {
      Member member = new Member();
      member.createMember("1", "1", "1", 1);

      memberService.join(member);
   }
   
   @Test
   @Rollback(value = false)
   public void 문제리스트전체조회() throws Exception {
       //given
      Member findMember = memberService.findByUserId("1").get();

      Post post1 = new Post();
      post1.createPost(findMember, "123","123","123");

      Post post2 = new Post();
      post2.createPost(findMember, "456","456","456");

      postService.regPost(post1);
      postService.regPost(post2);

      //when
      List<Post> posts = postService.AllPostByUserId(findMember.getUserId());

      System.out.println("========================================");
      posts.stream().forEach(e -> System.out.println(e));

      //then
      Assertions.assertThat(posts.size()).isEqualTo(2);
   }

}