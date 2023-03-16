package project.AMS.awsS3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.AMS.awsS3.image.Image;
import project.AMS.awsS3.image.ImageRepository;
import project.AMS.awsS3.image.ImageService;
import project.AMS.awsS3.post.Article;
import project.AMS.awsS3.post.ArticleRepository;
import project.AMS.awsS3.post.ArticleService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final ArticleService articleService;
    private final ImageService imageService;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;


    //사진 저장
    //1. Request : multipart + form-data
    @PostMapping("/save-form-data")
    public String saveByFormData(@RequestParam("title") String title,
                           @RequestParam("context") String context,
                           @RequestParam("imageFileList") List<MultipartFile> lists) {

        //게시물 저장
        Long saveArticleId = articleService.saveArticle(title, context);

        //이미지 저장
        imageService.saveArticleImages(saveArticleId, lists);

        return "save success";
    }
    //2. Request : multipart + json
    @PostMapping(value = "/save-json", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String saveByJson(@RequestPart ArticleForm form,
                             @RequestPart List<MultipartFile> imgFiles) {

        Long saveArticleId = articleService.saveArticle(form.getTitle(), form.getContent());
        imageService.saveArticleImages(saveArticleId, imgFiles);
        return "save success";
    }

    //게시물 단건 조회
    @GetMapping("/{id}")
    public ArticleDto find(@PathVariable Long id){

        Article article = articleService.findArticle(id);
        List<Image> images = imageRepository.findByArticleId(id);

        return new ArticleDto(article, images);
    }

    //게시물 복수개 조회
    @GetMapping("/all")
    public List<ArticleDto> findAll() {

        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(a -> new ArticleDto(a, imageRepository.findByArticleId(a.getId())))
                .collect(Collectors.toList());
    }

    //게시물 삭제
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long articleId){

        imageService.deleteArticleImage(articleId);
        articleService.deleteArticle(articleId);

        return "delete success";
    }

    //게시물 수정(정보, 사진 수정)
    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String update(@PathVariable("id") Long articleId,
                         @RequestPart ArticleEditForm form,
                         @RequestParam List<MultipartFile> images){

        articleService.editArticle(articleId, form.getTitle(), form.getContent());
        imageService.editArticleImages(articleId, images);

        return "edit success";
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ArticleForm {
        private String title;
        private String content;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ArticleEditForm {
        private String title;
        private String content;
    }
    @Data
    static class ArticleDto {
        private Long id;
        private String title;
        private String content;
        List<String> images;

        public ArticleDto(Article article, List<Image> images) {
            this.id = article.getId();
            this.title = article.getTitle();
            this.content = article.getContent();
            this.images = images.stream().map( image -> new String(image.getImageUrl())).collect(Collectors.toList());
        }
    }

}
