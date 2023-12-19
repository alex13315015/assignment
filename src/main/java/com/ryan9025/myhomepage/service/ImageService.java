package com.ryan9025.myhomepage.service;

import com.ryan9025.myhomepage.dto.CustomUserDetails;
import com.ryan9025.myhomepage.dto.ImageUploadDto;
import com.ryan9025.myhomepage.entity.Image;
import com.ryan9025.myhomepage.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    public void upload(ImageUploadDto imageUploadDto, CustomUserDetails customUserDetails) {
        String originalFileName = imageUploadDto.getFile().getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + originalFileName;
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        try {
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image image = imageUploadDto.toEntity(imageFileName,customUserDetails.getLoggedMember());
        imageRepository.save(image);
    }
    public Page<Image> loadFeed(int customDetailsID, Pageable pageable) {
        Page<Image> images = imageRepository.loadFeed(customDetailsID,pageable);
        images.forEach((image) -> {
            image.setLikeTotal(image.getLikes().size());
            log.info("image.getLikes().size()==={}",image.getLikes().size());
            image.getLikes().forEach((like) -> {
                if(like.getMember().getId() == customDetailsID) {
                    image.setLikeState(true);
                }
            });
        });
     return images;
    }
}
