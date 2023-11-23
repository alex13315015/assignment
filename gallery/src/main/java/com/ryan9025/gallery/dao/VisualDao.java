package com.ryan9025.gallery.dao;

import com.ryan9025.gallery.dto.VisualDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface VisualDao {

    int insertVisual(VisualDto visualDto);

    List<VisualDto> getAllVisualList();
}
