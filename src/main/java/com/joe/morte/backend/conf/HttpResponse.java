package com.joe.morte.backend.conf;

import java.io.Serializable;

/**
 * Created by yiikou on 2018-07-28.
 */
public class HttpResponse implements Serializable {
    private static final long serialVersionUID = 1810776104633130063L;

    private boolean success;//
    private String code;//
    private String description;//
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
