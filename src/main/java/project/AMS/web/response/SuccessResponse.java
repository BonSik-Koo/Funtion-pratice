package project.AMS.web.response;

import lombok.Data;

@Data
public class SuccessResponse<T> {

    private String status;
    private String message;
    private T data;

    public SuccessResponse(String s, String s1, T data) {
        this.status = s;
        this.message = s1;
        this.data = data;
    }

}
