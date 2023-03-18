package project.AMS.awsS3.s3;

public class FileDownloadFailedException extends RuntimeException{
    public FileDownloadFailedException() {
    }

    public FileDownloadFailedException(String message) {
        super(message);
    }
}
