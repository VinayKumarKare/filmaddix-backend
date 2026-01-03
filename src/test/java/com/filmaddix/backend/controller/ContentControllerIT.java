//package com.filmaddix.backend.controller;
//
//import com.filmaddix.backend.service.ContentService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ContentController.class)
//class ContentControllerIT {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ContentService contentService;
//
//    @Test
//    void homePage_shouldReturn200() throws Exception {
//        mockMvc.perform(get("/api/v1/contents/home"))
//                .andExpect(status().isOk());
//    }
//}
