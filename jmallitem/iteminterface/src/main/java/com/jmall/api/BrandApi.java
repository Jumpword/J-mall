package com.jmall.api;

import com.jmall.entity.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author jumping-張文平
 * @Date 2019/10/31 14:15
 * @Version 1.0
 */
public interface BrandApi {
    @GetMapping("brand/{id}")
    ResponseEntity<Brand> queryBrandById(@PathVariable("id") Long id);
}
