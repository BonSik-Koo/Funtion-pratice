package project.AMS.domain.post.service;

import project.AMS.domain.post.Post;

import java.util.List;

public interface PostService {

    public Long regPost(Post postForm);
    public void editPost(Post postForm) ;
    public void delPost(Post postForm);

    public List<Post> AllPost(); //모든 게시물 리스트
    public List<Post> AllPostByUserId(String userId); //작성자에 따른 게시물 리스트

    public Post findPostByPostId(Long id); //게시물 id로 찾기


}

