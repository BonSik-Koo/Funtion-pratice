package project.AMS.awsS3.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.AMS.awsS3.image.ImageRepository;
import project.AMS.awsS3.image.ImageService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ImageService imageService;

    @Transactional
    public void saveArticle(String title, String context, List<MultipartFile> files) {

        //게시물 정보 저장
        Article post = new Article(title, context);
        articleRepository.save(post);

        //게시물 이미지 저장
        imageService.saveImages(post, files);
    }

    public Article findArticle(Long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("존재하지 않은 게시물 입니다."));
    }

    @Transactional
    public void deleteArticle(Long articleId) {

        //이미지 삭제
        imageService.deleteImageByArticleId(articleId);

        //엔티티 삭제
        articleRepository.deleteById(articleId);
    }


}
