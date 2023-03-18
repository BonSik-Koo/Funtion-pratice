package project.AMS.awsS3.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

@Slf4j
public class CommonUtils {

    public static MediaType contentType(String fileName) {

        String[] arr = fileName.split("\\.");
        String type = arr[arr.length-1];

        switch(type) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public static ContentDisposition createContentDisposition(String fileName) {
        return  ContentDisposition.builder("attachment")
                .filename(fileName, StandardCharsets.UTF_8)
                .build();
    }

}
