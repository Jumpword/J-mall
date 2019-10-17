package com.jamll.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jmall.enums.ExceptionEnum;
import com.jmall.exception.JmallException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
    @Autowired
    private FastFileStorageClient storageClient;
    private static final List<String> ALLOW_TYPES = Arrays.asList("image/png","image/jpeg","image/bmp");
    public String uploadImage(MultipartFile file) {
        try {
            //检验文件类型
            String contentType = file.getContentType();
            if(!ALLOW_TYPES.contains(contentType)){
                throw new JmallException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //检验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image==null){
                throw new JmallException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //保存文件到FastDFS（分布式文件系统）Tracker-做集群：追踪定位文件存储到那个Storage（多台分布存储）里面
            //1.获取文件后缀名
            /*String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);*/
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            //返回一个访问路径
            return "http://image.jmall.com/"+storePath.getFullPath();
        } catch (IOException e) {
            log.error("上传图片失败",e);
            throw new JmallException(ExceptionEnum.FILE_UPLOAD_ERROR);
        } finally {

        }
    }
}
