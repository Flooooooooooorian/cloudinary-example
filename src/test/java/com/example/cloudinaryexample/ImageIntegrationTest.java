package com.example.cloudinaryexample;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;


import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ImageIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Cloudinary cloudinary;

    Uploader uploader = mock(Uploader.class);

    @Test
    void uploadImage() throws Exception {

        when(cloudinary.uploader()).thenReturn(uploader);
        when(uploader.upload(any(), anyMap())).thenReturn(Map.of("url", "testurl"));

        MockMultipartFile mockFile = new MockMultipartFile("file", "content".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(multipart("/api/images")
                        .file(mockFile)
                        .file("data", """
                                {
                                "name": "testName"
                                }
                                """.getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isOk())
                .andExpect(content().string("testurl"));
    }

}
