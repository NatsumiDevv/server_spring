package com.devPn.socialmedias.service;



import com.devPn.socialmedias.maper.UserImageMapper;
import com.devPn.socialmedias.model.UserImage;
import com.devPn.socialmedias.model.response.UserImageResponse;
import com.devPn.socialmedias.reponsitory.UserImageRepository;
import com.devPn.socialmedias.reponsitory.UserRepository;
import com.devPn.socialmedias.utils.ImgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Slf4j
@Service
public class UserImageService {
    @Autowired
    private UserImageRepository userImageRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private UserImageMapper userImageMapper;

    @Autowired
    private UserRepository userRepository;




    public UserImage upload(MultipartFile fileRequest, int userId) throws IOException {
        if (userRepository.findById(userId).isPresent()) {
            UserImage userImage = userImageRepository.save(UserImage.builder()
                    .name(fileRequest.getOriginalFilename())
                    .type(fileRequest.getContentType())
                    .data(fileRequest.getBytes()) // Lấy dữ liệu ảnh
                    .user(userRepository.findByUserId(userId))
                    .build());

            return userImage;
        }
        return null;
    }


    public byte[] downloadAvatar(int userId) {
        Optional<UserImage> userImage = userImageRepository.findUserImageByUser_UserId(userId);
        return userImage.map(UserImage::getData).orElse(null);
    }








}
