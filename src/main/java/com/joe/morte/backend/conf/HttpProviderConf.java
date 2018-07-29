package com.joe.morte.backend.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by yiikou on 2018-07-28.
 */
@Configuration
public class HttpProviderConf {
    /**
     * Provide package for http visiting
     */
    @Value("#{'${dubbo.api.package}'.split(',')}")
    private List<String> usePackage ;

    public List<String> getUsePackage() {
        return usePackage;
    }

    public void setUsePackage(List<String> usePackage) {
        this.usePackage = usePackage;
    }
}
