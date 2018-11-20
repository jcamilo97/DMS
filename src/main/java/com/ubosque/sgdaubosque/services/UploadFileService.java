package com.ubosque.sgdaubosque.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {
    private  String upload_path = ".//src//main//resources//files//";

    public  String  saveFile(MultipartFile file) throws IOException {
        String pathUrl = "";
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload_path + file.getOriginalFilename());
            Files.write(path,bytes);
            pathUrl = path.toString();
        }
        return  pathUrl;
    }
}
