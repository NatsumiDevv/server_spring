package com.devPn.socialmedias.controller;


import com.devPn.socialmedias.model.UserImage;
import com.devPn.socialmedias.model.response.UserImageResponse;
import com.devPn.socialmedias.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@RestController
@RequestMapping("/api/userimages")
public class UserImageController {
    @Autowired
    UserImageService userImageService;
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("image") MultipartFile file, @RequestParam int userId) throws IOException {
        UserImage uploadImage = userImageService.upload(file, userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/download/{userId}")
    public ResponseEntity<?> download(@PathVariable int userId) throws IOException {
        byte[] image = userImageService.downloadAvatar(userId);
        if (image!=null){
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND) // Trả về 404 khi không có ảnh
                .body("Image not found for userId: " + userId);

    }

    @GetMapping("/download/avatar.png")
    public ResponseEntity<?> download() throws IOException {
        String filePath = "C:\\Users\\phuoc\\Documents\\socialmedia-reactjs\\socialmedias\\uploads\\avatar.jpg";
        byte[] image = Files.readAllBytes(new File(filePath).toPath());
        if(image!=null){
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Image not found");
    }

}