package com.ryan9025.gallery.service;

import com.ryan9025.gallery.dao.GalleryDao;
import com.ryan9025.gallery.dto.GalleryDto;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalleryService {


    @Value("${file.path}")
    private String uploadFolder;


    private final GalleryDao galleryDao;

    public List<GalleryDto> getAllList() {
        List<GalleryDto> galleryList = galleryDao.getAllList();
        return galleryList;
    }
    public int insertGallery(GalleryDto galleryDto)  {
        log.info("uploadFolder==={}",uploadFolder);
        //log.info("userName==={}",userName);


        UUID uuid = UUID.randomUUID();
        log.info("originalFileName==={}",galleryDto.getFile().getOriginalFilename());
        String originalFile = galleryDto.getFile().getOriginalFilename();
        String renamedFile = uuid + "_" + originalFile ;
        log.info("originalFileName==={}===renamedFile==={}",originalFile,renamedFile);

        Path imgFilePath = Paths.get(uploadFolder + renamedFile);


        galleryDto.setOriginal(originalFile);
        galleryDto.setRenamed(renamedFile);

        try {
            Files.write(imgFilePath,galleryDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int result = galleryDao.insertGallery(galleryDto);
        return result;
    }

    }
