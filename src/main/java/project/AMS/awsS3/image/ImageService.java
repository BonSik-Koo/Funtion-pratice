package project.AMS.awsS3.image;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.AMS.awsS3.post.Article;
import project.AMS.awsS3.s3.FileFolder;
import project.AMS.awsS3.s3.FileProcessService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;
    private final FileProcessService fileProcessService;

    @Transactional
    public void savePostImages(Article article, List<MultipartFile> files) {

        for(MultipartFile multipartFile : files) {
            String url = fileProcessService.uploadImage(multipartFile, FileFolder.POST_IMAGES);
            imageRepository.save(new Image(url, article));
        }
    }

}
