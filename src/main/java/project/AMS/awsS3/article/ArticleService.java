package project.AMS.awsS3.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.AMS.awsS3.image.ImageService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ImageService imageService;

    @Transactional
    public Long saveArticle(String title, String context) {

        //게시물 정보 저장
        Article article = new Article(title, context);
        articleRepository.save(article);

        return article.getId();
    }

    public Article findArticle(Long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("존재하지 않은 게시물 입니다."));
    }

    @Transactional
    public void deleteArticle(Long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NullPointerException("존재하지 않은 게시물 입니다."));

        articleRepository.deleteById(article.getId());
    }

    @Transactional
    public void editArticle(Long articleId, String title, String context) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NullPointerException("존재하지 않은 게시물 입니다."));

        article.setArticle(title, context);
    }

}
