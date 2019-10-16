package com.jamll.service;

import com.jmall.enums.ExceptionEnum;
import com.jmall.exception.JmallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author jumping-張文平
 * @Date 2019/10/16 19:09
 * @Version 1.0
 */
@Service
@Slf4j
public class UploadService {
    private static final List<String> types = Arrays.asList("image/png","image/jpeg","image/bmp");
    public String uploadImage(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            //准备目标路径
            File dest = new File("F:\\IdeaWork\\upload\\",file.getOriginalFilename());
            //保存文件到本地
            file.transferTo(dest);
            //返回一个访问路径
            return "http://image.jmall.com/"+file.getOriginalFilename();
        } catch (IOException e) {
            log.error("上传图片失败",e);
            throw new JmallException(ExceptionEnum.IMAGE_UPLOAD_ERROR);
        } finally {

        }
    }
}
