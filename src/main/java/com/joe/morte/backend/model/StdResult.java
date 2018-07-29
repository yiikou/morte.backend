package com.joe.morte.backend.model;
import java.io.Serializable;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
/**
 * Created by yiikou on 2018-07-25.
 */
public class StdResult implements Serializable {
    private static final long serialVersionUID = 3339559575209797644L;
    private boolean isSuccess = false;
    private String msg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
//
//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE);
//    }
}
