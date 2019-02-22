package com.embed.detection.util;

import com.embed.detection.model.Box;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@Scope("singleton")
public class FileUtils {

    @Value("${upload.path}")
    private String uploadDirPath;

    @Value("${prediction.path}")
    private String predictionDirPath;

    @Autowired
    DetectUtil detectUtil;

    public List<Box> saveAndPredictUploadImg(MultipartFile file, String randomName) throws Exception {
        if (file.isEmpty()) return null;

        String suffix = file.getOriginalFilename().split("\\.")[1];

        String uploadFilePath = uploadDirPath + randomName+ "." + suffix;
        String storeFilePath = predictionDirPath + randomName;
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadFilePath);
        Files.write(path, bytes);
        return detectUtil.getDetectedBoxes(uploadFilePath, storeFilePath);
    }


}