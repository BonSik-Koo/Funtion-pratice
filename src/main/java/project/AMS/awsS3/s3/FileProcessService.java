package project.AMS.awsS3.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileProcessService {

    private final FileService fileService;

    public String uploadImage(MultipartFile file, FileFolder fileFolder) {

        //파일 이름 생성
        String fileName = fileService.getFileFolder(fileFolder) + createFileName(file.getOriginalFilename());

        //파일 변환??
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        //파일 업로드
        try(InputStream inputStream = file.getInputStream()) {
            fileService.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다. (%s)", file.getOriginalFilename()));
        }
        return fileService.getFileUrl(fileName);
    }

    public void deleteImage(String url){
        fileService.deleteFile(convertToFileName(url));
    }


    //파일 이름 생성 로직
    private String createFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    //파일의 확장자명을 가져오는 로직
    private String getFileExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        }catch(StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다.",fileName));
        }
    }

    //이미지 URL->파일 이름 변환
    private String convertToFileName(String imageUrl){
        String[] path = imageUrl.split("/");
        return path[path.length-2] + path[path.length-1];  //폴더 이름 + 파일 이름
    }

}
