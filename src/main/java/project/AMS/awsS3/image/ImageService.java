package project.AMS.awsS3.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.AMS.awsS3.post.Article;
import project.AMS.awsS3.post.ArticleRepository;
import project.AMS.awsS3.s3.FileFolder;
import project.AMS.awsS3.s3.FileProcessService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;
    private final ArticleRepository articleRepository;
    private final FileProcessService fileProcessService;

    @Transactional
    public void saveArticleImages(Long articleId, List<MultipartFile> files) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NullPointerException("존재하지 않는 게시물입니다."));

        for(MultipartFile multipartFile : files) {
            String url = fileProcessService.uploadImage(multipartFile, FileFolder.POST_IMAGES);
            imageRepository.save(new Image(url, article));
        }
    }

    @Transactional
    public void deleteArticleImage(Long articleId){

        List<Image> images = imageRepository.findByArticleId(articleId);

        for(Image image : images) {
            //이미지 저장소 삭제
            fileProcessService.deleteImage(image.getImageUrl());
            //엔티티 삭제
            imageRepository.deleteById(image.getId());
        }
    }

    /**
     * <이미지 수정 순서>
     * 1. 기존 이미지 모두 삭제
     * 2. 새로운 이미지 저장
     */
    @Transactional
    public void editArticleImages(Long articleId, List<MultipartFile> images) {

        //기존 이미지 삭제
        deleteArticleImage(articleId);

        //새로운 이미지 업데이트
        saveArticleImages(articleId, images);
    }

}
