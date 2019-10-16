package com.jamll.web;

import com.jamll.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author jumping-張文平
 * @Date 2019/10/16 19:01
 * @Version 1.0
 */

@RestController
@RequestMapping("upload")
public class UploadContoller {

    @Autowired
    private UploadService uploadService;

    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file){
        String url  = uploadService.uploadImage(file);
        return ResponseEntity.ok(url);
    }
}
