package project.AMS.awsS3.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface FileService {

    //파일 업로드
    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);

    //파일 삭제
    void deleteFile(String fileName);

    //파일 URL 조히
    String getFileUrl(String fileName);

    //폴더 조회
    String getFileFolder(FileFolder fileFolder);

}
