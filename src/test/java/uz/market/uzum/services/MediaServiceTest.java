package uz.market.uzum.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@ContextConfiguration(classes = {MediaService.class})
@ExtendWith(SpringExtension.class)
class MediaServiceTest {
    @Autowired
    private MediaService mediaService;

    @Test
    void testUpload() throws IOException {
        mediaService.upload(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }
}

