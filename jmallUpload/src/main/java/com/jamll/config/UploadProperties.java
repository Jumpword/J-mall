package com.jamll.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author jumping-張文平
 * @Date 2019/10/17 20:18
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "jmall.upload")
public class UploadProperties {
    private String baseUrl;
    private List<String> allowTypes;
}
