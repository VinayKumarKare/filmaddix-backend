//package com.filmaddix.backend.service;
//
//import com.filmaddix.backend.dto.ContentDetailDto;
//import com.filmaddix.backend.dto.ContentListDto;
//import com.filmaddix.backend.entity.Content;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ContentMapper {
//
//    public ContentListDto toListDto(Content content) {
//        return ContentListDto.builder()
//                .id(content.getId())
//                .title(content.getTitle())
//                .contentType(content.getContentType())
//                .posterUrl(content.getPosterUrl())
//                .languages(
//                        content.getLanguages()
//                                .stream()
//                                .map(l -> l.getLanguage())
//                                .toList()
//                )
//                .build();
//    }
//
//    public ContentDetailDto toDetailDto(Content content) {
//        return ContentDetailDto.builder()
//                .id(content.getId())
//                .title(content.getTitle())
//                .description(content.getDescription())
//                .contentType(content.getContentType())
//                .languages(
//                        content.getLanguages()
//                                .stream()
//                                .map(l -> l.getLanguage())
//                                .toList()
//                )
//                .genres(
//                        content.getGenres()
//                                .stream()
//                                .map(g -> g.getName())
//                                .toList()
//                )
//                .releases(content.getReleases())
//                .mediaAssets(content.getMediaAssets())
//                .build();
//    }
//}
