package com.jmall.client;
import com.jmall.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * @Author jumping-張文平
 * @Date 2019/10/31 13:25
 * @Version 1.0
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
