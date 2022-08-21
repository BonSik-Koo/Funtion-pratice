package project.AMS.domain.post.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.AMS.domain.hashtag.hashtag.Hashtag;
import project.AMS.domain.hashtag.hashtag.HashtagRepository;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.service.MemberService;
import project.AMS.domain.post.Post;
import project.AMS.web.apiController.form.post.PostAndHashtagForm;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired public MemberService memberService;
    @Autowired public PostService postService;
    @Autowired public HashtagRepository hashtagRepository;


    @BeforeEach
    public void 초기DB() {
        Member member = new Member();
        member.createMember("test", "test", "test", 1);
        memberService.join(member);
    }


    @Test
    //@Rollback(value = false)
    public void 기존에없는해시태그에대한해시태그를포함한게시물저장테스트() throws Exception {

        //given
        Member member = memberService.findByUserId("test").get();

        List<String> hash = new ArrayList<>();
        hash.add("hashtag1"); hash.add("hashtag2");
        PostAndHashtagForm postAndHashtagForm = new PostAndHashtagForm("test1", "test1", "test1",hash );

        //when
        Long postId = postService.regPostAndHashTag(postAndHashtagForm, member.getUserId());

        //then
        Post findPost = postService.findPostByPostId(postId);
        Assertions.assertThat(findPost.getPostHashtags().size()).isEqualTo(2);
    }

    @Test
    //@Rollback(value = false)
    public void 기존에있는해시태그에대한해시태그를포함한게시물저장테스트() throws Exception {
        //given

        Member member = memberService.findByUserId("test").get();

        Hashtag tag1 = Hashtag.createHashtag("구본식");
        Hashtag tag2 = Hashtag.createHashtag("양소은");
        hashtagRepository.save(tag1);
        hashtagRepository.save(tag2);

        List<String> hash = new ArrayList<>();
        hash.add("구본식"); hash.add("양소은");
        PostAndHashtagForm postAndHashtagForm = new PostAndHashtagForm("test1", "test1", "test1",hash );

        //when
        Long postId = postService.regPostAndHashTag(postAndHashtagForm, member.getUserId());

        //then
        Post findPost = postService.findPostByPostId(postId);
        Assertions.assertThat(findPost.getPostHashtags().size()).isEqualTo(2);
    }

}