package com.ryan9025.gallery.dao;

import com.ryan9025.gallery.dto.GalleryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryDao {

    int insertGallery(GalleryDto galleryDto);

    List<GalleryDto> getAllList();
}
