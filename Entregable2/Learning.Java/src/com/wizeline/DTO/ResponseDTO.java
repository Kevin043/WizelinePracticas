package com.wizeline.DTO;

public class ResponseDTO {

    private String status;
    private String code;
    private  ErrorDTO error = new ErrorDTO();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setErrors(ErrorDTO error) {
        this.error = error;
    }
}
