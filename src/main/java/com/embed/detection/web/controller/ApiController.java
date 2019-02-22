package com.embed.detection.web.controller;

import com.embed.detection.model.Box;
import com.embed.detection.util.DetectUtil;
import com.embed.detection.util.FileUtils;
import com.embed.detection.util.RenameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("${upload.path}")
    private String uploadDirPath;

    @Value("${prediction.path}")
    private String predictionDirPath;

    @Autowired
    FileUtils fileUtils;


    private ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

    @PostMapping("/getDetectedImage")
    public ResponseEntity<FileSystemResource> getrDetectedImage(@RequestParam("file") MultipartFile file) throws Exception {
        String randomName = RenameUtil.creatFileName();
        String storeFilePath = predictionDirPath + randomName + "." +file.getOriginalFilename().split("\\.")[1];
        fileUtils.saveAndPredictUploadImg(file, randomName);
        return export(new File(storeFilePath));
    }

    @PostMapping("/getDetectedBoxes")
    public List<Box> getDetectedBoxes(@RequestParam("file") MultipartFile file) throws Exception {
        String randomName = RenameUtil.creatFileName();
        return fileUtils.saveAndPredictUploadImg(file, randomName);

    }
}
