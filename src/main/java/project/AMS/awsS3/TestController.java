package project.AMS.awsS3;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.AMS.awsS3.image.Image;
import project.AMS.awsS3.image.ImageRepository;
import project.AMS.awsS3.post.Article;
import project.AMS.awsS3.post.ArticleRepository;
import project.AMS.awsS3.post.ArticleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;


    @PostMapping("/save")
    public String save(@RequestParam("title") String title,
                           @RequestParam("context") String context,
                           @RequestParam("imageFileList") List<MultipartFile> lists) {

        articleService.savePost(title, context, lists);
        return "success";
    }

    @GetMapping("/{id}")
    public ListDto find(@PathVariable Long id){

        Article article = articleRepository.findById(id).orElseThrow(() -> new NullPointerException("존재하지 않는 글입니다."));
        List<Image> images = imageRepository.findByArticleId(id);

        return new ListDto(article, images);
    }

    @Data
    class ListDto {
        private Long id;
        private String title;
        private String context;
        List<String> images;

        public ListDto(Article article, List<Image> images) {
            this.id = article.getId();
            this.title = article.getTitle();
            this.context = article.getContext();
            this.images = images.stream().map( image -> new String(image.getImageUrl())).collect(Collectors.toList());
        }
    }

}
