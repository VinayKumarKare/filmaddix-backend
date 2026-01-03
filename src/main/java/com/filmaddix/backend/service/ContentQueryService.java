//package com.filmaddix.backend.service;
//
//import com.filmaddix.backend.dto.ContentDetailDto;
//import com.filmaddix.backend.dto.ContentListDto;
//import com.filmaddix.backend.entity.Content;
//import com.filmaddix.backend.exception.ResourceNotFoundException;
//import com.filmaddix.backend.repository.ContentRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true)
//public class ContentQueryService {
//
//    private final ContentRepository contentRepository;
//    private final ContentMapper contentMapper;
//
//    public ContentQueryService(ContentRepository contentRepository,
//                               ContentMapper contentMapper) {
//        this.contentRepository = contentRepository;
//        this.contentMapper = contentMapper;
//    }
//
//    // 🔍 Discovery page
//    public List<ContentListDto> getDiscoveryContents() {
//        return contentRepository.findAllForDiscovery()
//                .stream()
//                .map(contentMapper::toListDto)
//                .toList();
//    }
//
//    // 🔎 Detail page
//    public ContentDetailDto getContentDetail(Long contentId) {
//        Content content = contentRepository.findByIdWithDetails(contentId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException(
//                                "Content not found with id " + contentId));
//
//        return contentMapper.toDetailDto(content);
//    }
//}
