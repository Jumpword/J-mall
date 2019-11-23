package com.jmall.client;

import com.jmall.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author jumping-張文平
 * @Date 2019/11/22 14:23
 * @Version 1.0
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
