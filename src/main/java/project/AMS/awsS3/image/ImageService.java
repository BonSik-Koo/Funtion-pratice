package project.AMS.awsS3.image;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.AMS.awsS3.post.Article;
import project.AMS.awsS3.s3.FileFolder;
import project.AMS.awsS3.s3.FileProcessService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;
    private final FileProcessService fileProcessService;

    @Transactional
    public void saveImages(Article article, List<MultipartFile> files) {

        for(MultipartFile multipartFile : files) {
            String url = fileProcessService.uploadImage(multipartFile, FileFolder.POST_IMAGES);
            imageRepository.save(new Image(url, article));
        }
    }

    @Transactional
    public void deleteImageByArticleId(Long articleId){

        List<Image> images = imageRepository.findByArticleId(articleId);
        for(Image image : images) {
            //이미지 저장소 삭제
            fileProcessService.deleteImage(image.getImageUrl());

            //엔티티 삭제
            imageRepository.deleteById(image.getId());
        }
    }

}
