package project.AMS.awsS3.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface FileService {

    //파일 업로드
    String uploadFile(MultipartFile file, FileFolder fileFolder);

    //파일 삭제
    void deleteFile(String fileName);

    //파일 URL 조회
    String getFileUrl(String fileName);

    //파일 다운로드
    byte[] downloadFile(String fileName) throws FileNotFoundException;

    //폴더 조회
    String getFileFolder(FileFolder fileFolder);

}
