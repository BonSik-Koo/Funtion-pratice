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
    private final ImageRepository imageRepository;

    @Transactional
    public void savePost(String title, String context, List<MultipartFile> files) {

        //게시물 정보 저장
        Article post = new Article(title, context);
        articleRepository.save(post);

        //게시물 이미지 저장
        imageService.savePostImages(post, files);
    }


}
