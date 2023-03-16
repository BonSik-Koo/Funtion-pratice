package project.AMS.awsS3.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface FileService {

    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);

    void deleteFile(String fileName);
    String getFileUrl(String fileName);

    String getFileFolder(FileFolder fileFolder);

}
