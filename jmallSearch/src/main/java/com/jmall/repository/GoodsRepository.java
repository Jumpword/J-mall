package com.jmall.repository;

import com.jmall.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author jumping-張文平
 * @Date 2019/11/22 13:15
 * @Version 1.0
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

}
