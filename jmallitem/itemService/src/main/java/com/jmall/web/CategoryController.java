package com.jmall.web;
import com.jmall.entity.Category;
import com.jmall.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Author jumping-張文平
 * @Date 2019/10/11 14:40
 * @Version 1.0
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;


   /**
    * 根据父节点的id查找商品
    * @param pid
    * @data 2019/10/11 15:48
    * [pid]
    * @return org.springframework.http.ResponseEntity<java.util.List<com.jmall.entity.Category>>
    */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam("pid")Long pid){
        List<Category> categories = categoryService.queryCategoryByPid(pid);
        return ResponseEntity.ok(categories);
    }


}
