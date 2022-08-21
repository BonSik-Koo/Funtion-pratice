package project.AMS.domain.post.service;

import project.AMS.domain.post.Post;
import project.AMS.web.apiController.form.post.PostAndHashtagForm;

import java.util.List;

public interface PostService {

    public Long regPost(Post postForm);
    public void editPost(Post postForm) ;
    public void delPost(Post postForm);

    public List<Post> AllPost(); //모든 게시물 리스트
    public List<Post> AllPostByUserId(String userId); //작성자에 따른 게시물 리스트

    public Post findPostByPostId(Long id); //게시물 id로 찾기


    /**
     * 해시태그 기능
     */
    public Long regPostAndHashTag(PostAndHashtagForm postForm, String userId);


    /**
     * 해시 태그 포함한 조회 (단일, 복수 둘다 가능해야된다??)
     */
    public List<Post> findAllPostByUserIdAndHashTag(String userId, String tag);
}

