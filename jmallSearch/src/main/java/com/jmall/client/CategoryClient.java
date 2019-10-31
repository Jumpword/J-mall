package com.jmall.client;
import com.jmall.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * @Author jumping-張文平
 * @Date 2019/10/30 21:06
 * @Version 1.0
 */

@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
