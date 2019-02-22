package com.embed.detection.web.controller;

import com.embed.detection.util.FileUtils;
import com.embed.detection.util.RenameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class ViewController {

    private Logger logger = LoggerFactory.getLogger(ViewController.class);

    private final ResourceLoader resourceLoader;

    @Value("${prediction.path}")
    private String predictionDirPath;

    @Autowired
    FileUtils fileUtils;

    @Autowired
    public ViewController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @RequestMapping("test")
    public String toUpload(){
        return "show";
    }

    @RequestMapping("fileUpload")
    public String upload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map) throws Exception{
        String randomName = RenameUtil.creatFileName();
        fileUtils.saveAndPredictUploadImg(file, randomName);

        String path =  predictionDirPath + randomName + "." +file.getOriginalFilename().split("\\.")[1];

        map.put("msg", "上传成功");
        map.put("fileName", path);

        return "forward:/test";
    }

    @RequestMapping("show")
    public ResponseEntity showPhotos(String fileName){
        try {
            logger.info(fileName);
            return ResponseEntity.ok(resourceLoader.getResource("file:" + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
