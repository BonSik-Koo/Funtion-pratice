package project.AMS.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.AMS.domain.hashtag.hashtag.Hashtag;
import project.AMS.domain.hashtag.hashtag.HashtagRepository;
import project.AMS.domain.hashtag.posthashtag.PostHashtag;
import project.AMS.domain.member.Member;
import project.AMS.domain.member.repository.MemberRepository;
import project.AMS.domain.post.Post;
import project.AMS.domain.post.repository.PostRepository;
import project.AMS.web.apiController.form.post.PostAndHashtagForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;
    private final MemberRepository memberRepository;

    public Long regPost(Post postForm) {
        Post write = postRepository.write(postForm);
        return write.getId();
    }

    public void editPost(Post postForm) {
        postRepository.edit(postForm.getId(), postForm);
    }

    public void delPost(Post postForm) {
        postRepository.delete(postForm);
    }


    public List<Post> AllPost() {
        List<Post> findAllPost = postRepository.findAll();
        return findAllPost;
    }

    public List<Post> AllPostByUserId(String userId) {
        List<Post> findAllPost = postRepository.findAllByUserId(userId);
        return findAllPost;
    }


    public Post findPostByPostId(Long id) {
        Post findPost = postRepository.findByPostId(id);
        return findPost;
    }



    /**
     * 해시태그를 포함한 게시물 저장 기능
     */
    public Long regPostAndHashTag(PostAndHashtagForm postForm, String userId) {

        Optional<Member> findMember = memberRepository.findByUserId(userId);

        //post 생성
        Post post = new Post();
        post.createPost(findMember.get(), postForm.getTitle(), postForm.getProblem_uri(),postForm.getContent());


        //hashtag 생성
        List<String> hashtags = postForm.getHashtag();
        //사용자가 입력한 해시태그가 기존에 있는건지 아닌지 확인!
        for(String hashtagName : hashtags) {
            Optional<Hashtag> findHashtag = hashtagRepository.findByName(hashtagName);
            if(!findHashtag.isPresent()) { //기존에 존재하지 않은 해시태그
                hashtagRepository.save(Hashtag.createHashtag(hashtagName));
            }
        }

        //postHashtag 생성
        for(String Hashtag : hashtags) {
            Optional<Hashtag> findHashtag = hashtagRepository.findByName(Hashtag);
            PostHashtag postHashtag = PostHashtag.createPostHashtag(findHashtag.get());

            post.addPostHashtag(postHashtag);
        }

        //post 저장
        Post savePost = postRepository.write(post);
        return savePost.getId();
    }

    /**
     * 해시태그를 포함한 게시물 검색 기능 -> 페이징도 추가하기
     * => 일단은 hashtag 가 하나일때만 동작하게 하였음!!
     */
    @Override
    public List<Post> findAllPostByUserIdAndHashTag(String userId, String tag) {

        List<Post> result = new ArrayList<>();

        //사용자의 모든 게시물 검색
        List<Post> findPost = postRepository.findAllByUserId(userId);

        for(Post post: findPost){
            List<PostHashtag> postHashtags = post.getPostHashtags(); //프록시 초기화

            for(PostHashtag postHashtag : postHashtags) {

                String tagName = postHashtag.getHashtag().getName();
                if(tagName.equals(tag))
                    result.add(post);
            }

        }

        return result;
    }

}
