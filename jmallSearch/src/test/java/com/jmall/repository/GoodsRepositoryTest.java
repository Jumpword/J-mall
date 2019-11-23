package com.jmall.repository;

import com.jmall.pojo.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author jumping-張文平
 * @Date 2019/11/22 13:21
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest {
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchTemplate template;

    //创建索引库
    @Test
    public void testCreateIndex(){
        //添加索引
        template.createIndex(Goods.class);
        //添加映射
        template.putMapping(Goods.class);
    }

}