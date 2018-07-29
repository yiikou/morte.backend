package com.joe.morte.backend.conf;


/**
 * Created by yiikou on 2018-07-28.
 */
public class HttpRequest {
    private String param;
    /*
    *interface API's package name (dubbo)
     */
    private String service;
    /*
    *function's name
     */
    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getService() {

        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getParam() {

        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
