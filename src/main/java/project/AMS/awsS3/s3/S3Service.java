package project.AMS.awsS3.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class S3Service implements FileService{

    private final S3Component s3Component;
    private final AmazonS3 amazonS3;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3.putObject(
                new PutObjectRequest(s3Component.getBucket(), fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicReadWrite)
        );
    }

    @Override
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(s3Component.getBucket(), fileName));
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(s3Component.getBucket(), fileName).toString();
    }

    @Override
    public String getFileFolder(FileFolder fileFolder) {

        String folder = "";
        if(fileFolder == FileFolder.USER_IMAGES) {
            folder = s3Component.getUserFolder();

        }else if(fileFolder ==FileFolder.POST_IMAGES){
            folder = s3Component.getPostFolder();
        }
        return folder;
    }
}
