package com.ryan9025.myhomepage.dto;

import com.ryan9025.myhomepage.entity.Image;
import com.ryan9025.myhomepage.entity.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ImageUploadDto {

    private MultipartFile file;
    private String caption;

    public Image toEntity(String imageUrl, Member member) {
         return Image.builder()
                .imageUrl(imageUrl)
                .caption(caption)
                .member(member)
                .build();
    }
}
