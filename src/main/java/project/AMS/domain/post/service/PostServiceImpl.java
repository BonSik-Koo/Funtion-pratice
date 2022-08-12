package project.AMS.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.AMS.domain.member.Member;
import project.AMS.domain.post.Post;
import project.AMS.domain.post.repository.PostRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

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

    @Override
    public Post findPostByPostId(Long id) {
        Post findPost = postRepository.findById(id);
        return findPost;
    }

}
