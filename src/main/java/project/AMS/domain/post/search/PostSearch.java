package project.AMS.domain.post.search;

import lombok.Data;

import java.util.List;

@Data
public class PostSearch {

    //private List<String> tags; //해시 태그가 여러개일수도 있으니??!
    private String tags;
}
