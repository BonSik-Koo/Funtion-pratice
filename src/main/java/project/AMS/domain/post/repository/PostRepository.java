package project.AMS.domain.post.repository;

import project.AMS.domain.member.Member;
import project.AMS.domain.post.Post;

import java.util.List;

public interface PostRepository {

    public Post write(Post post);
    public void edit(Long id , Post post);
    public void delete(Post post);
    public List<Post> findAll(); //모든 커뮤니티 게시물 가져오기
    public List<Post> findAllByUserId(String userId); //사용자 아이디로 게시물들 찾기
    public Post findById(Long Id); //post 의 고유ID 로 찾기



}
