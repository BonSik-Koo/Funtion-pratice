package project.AMS.web.apiController.post;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.service.MemberService;
import project.AMS.domain.post.Post;
import project.AMS.domain.post.service.PostService;
import project.AMS.domain.reply.Reply;
import project.AMS.domain.reply.service.ReplyService;
import project.AMS.web.Dto.post.PostDto;
import project.AMS.web.Dto.post.PostListDto;
import project.AMS.web.SessionConst;
import project.AMS.web.apiController.form.post.PostAndHashtagForm;
import project.AMS.web.apiController.form.post.PostSaveForm;
import project.AMS.web.response.SuccessResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final ReplyService replyService;

    /**
     * 문제 리스트 api
     */
    @GetMapping("/{userId}")
    public SuccessResponse home(@PathVariable("userId") String userId) {

        //Member findMember = memberService.findByUserId(userId).get();
        List<Post> AllPost = postService.AllPostByUserId(userId);
        List<PostListDto> result = AllPost.stream().map(o -> new PostListDto(o))
                .collect(Collectors.toList());

        SuccessResponse response = new SuccessResponse<>("200", "해당 사용자의 문제 리스트 입니다.", result);
        return response;
    }
    /**
     * 문제 리스트 api v2
     * => 일단 hashtag 하나를 기준으로 검색하게 구현함 -> 여러개 있는건 나중에...
     */
    @GetMapping("/{userId}/v2")
    public SuccessResponse homeV2(@PathVariable("userId") String userId, @RequestParam(value = "tagName") String tagName) {
        List<Post> findPost = postService.findAllPostByUserIdAndHashTag(userId, tagName);

        List<PostListDto> postDtos = findPost.stream()
                .map(o -> new PostListDto(o))
                .collect(Collectors.toList());

        SuccessResponse response = new SuccessResponse("200","해시태그를 통한 문제 리스트 조회 결과입니다", postDtos);
        return response;
    }


    @GetMapping("/add")
    public String add(@ModelAttribute("postSaveForm")PostSaveForm postSaveForm, @SessionAttribute(value = SessionConst.LOGIN_MEMBER) String post_id
                      , Model model) {
        model.addAttribute("post_id" , post_id);
        return "post/addPostForm";
    }


    /**
     * 문제 등록 api - 본인만 가능하게 해야함
     */
    @PostMapping("/{userId}/add")
    public SuccessResponse add(@ModelAttribute PostSaveForm postSaveForm , @PathVariable("userId") String userId) {

        //정상적인 로직일때
        Optional<Member> findMember = memberService.findByUserId(userId);
        Member member = findMember.get();

        Post postForm = new Post();
        postForm.createPost(member, postSaveForm.getTitle(), postForm.getProblem_uri(), postForm.getContent());

        Long postId = postService.regPost(postForm); //문제 등록

        SuccessResponse response = new SuccessResponse("200", "정상적으로 문제가 등록되었습니다.", postId);
        return response;
    }
    /**
     * 문제 등록 api v2
     * => 저장은 다중 hashtag 에 대해서 json 형태로 받고 저장할수 있게 구현해놈
     */
    @PostMapping("/{userId}/addV2")
    public SuccessResponse add(@RequestBody PostAndHashtagForm postAndHashtagForm, @PathVariable("userId") String userId) {

        Long postId = postService.regPostAndHashTag(postAndHashtagForm, userId);
        return new SuccessResponse<>("200", "정상적으로 문제가 등록되었습니다.", postId);
    }



    /**
     * 문제 상세 조회 api
     * !여기서 문제에 달린 댓글도 같이 dto 반환해야함
     */
    @GetMapping("/{userId}/{postId}")
    public SuccessResponse home(@PathVariable(value = "userId") String userId , @PathVariable(value = "postId") Long postId) {

        Post findPost = postService.findPostByPostId(postId);
        List<Reply> allReply = replyService.findAllReply(postId);

        PostDto postDto = new PostDto(findPost, allReply);

        SuccessResponse response = new SuccessResponse<>("200", "문제 상세 조회 성공입니다.", postDto);
        return response;
    }

}
