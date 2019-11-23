package com.jmall.client;

import com.jmall.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author jumping-張文平
 * @Date 2019/11/22 13:10
 * @Version 1.0
 */
@FeignClient("item-service")
public interface SpecificationClient extends SpecificationApi {
}
