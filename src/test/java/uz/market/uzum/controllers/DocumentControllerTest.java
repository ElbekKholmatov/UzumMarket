package uz.market.uzum.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import uz.market.uzum.domains.Document;
import uz.market.uzum.repositories.DocumentRepository;
import uz.market.uzum.services.DocumentService;
import uz.market.uzum.services.MediaService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocumentControllerTest {
    @Test
    void testUploadFile() throws IOException {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        DocumentController documentController = new DocumentController(
                new DocumentService(documentRepository, new MediaService()));
        documentController
                .uploadFile(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }

    @Test
    void testUploadFile2() throws IOException {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        when(documentRepository.save(Mockito.<Document>any())).thenReturn(new Document());
        MediaService mediaService = mock(MediaService.class);
        when(mediaService.upload(Mockito.<MultipartFile>any())).thenReturn("Upload");
        DocumentController documentController = new DocumentController(
                new DocumentService(documentRepository, mediaService));
        ResponseEntity<Document> actualUploadFileResult = documentController
                .uploadFile(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        assertTrue(actualUploadFileResult.hasBody());
        assertTrue(actualUploadFileResult.getHeaders().isEmpty());
        assertEquals(200, actualUploadFileResult.getStatusCodeValue());
        verify(documentRepository).save(Mockito.<Document>any());
        verify(mediaService).upload(Mockito.<MultipartFile>any());
    }

    @Test
    void testUploadFile3() throws IOException {

        DocumentService documentService = mock(DocumentService.class);
        when(documentService.saveDocument(Mockito.<MultipartFile>any())).thenReturn(new Document());
        DocumentController documentController = new DocumentController(documentService);
        ResponseEntity<Document> actualUploadFileResult = documentController
                .uploadFile(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        assertTrue(actualUploadFileResult.hasBody());
        assertTrue(actualUploadFileResult.getHeaders().isEmpty());
        assertEquals(200, actualUploadFileResult.getStatusCodeValue());
        verify(documentService).saveDocument(Mockito.<MultipartFile>any());
    }

    @Test
    void testGetDocument() {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        when(documentRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Document()));
        ResponseEntity<Document> actualDocument = (new DocumentController(
                new DocumentService(documentRepository, new MediaService()))).getDocument(1L);
        assertTrue(actualDocument.hasBody());
        assertTrue(actualDocument.getHeaders().isEmpty());
        assertEquals(200, actualDocument.getStatusCodeValue());
        verify(documentRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testGetDocument2() {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        when(documentRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> (new DocumentController(new DocumentService(documentRepository, new MediaService()))).getDocument(1L));
        verify(documentRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testGetDocument4() {
        DocumentService documentService = mock(DocumentService.class);
        when(documentService.getDocument(Mockito.<Long>any())).thenReturn(Optional.of(new Document()));
        ResponseEntity<Document> actualDocument = (new DocumentController(documentService)).getDocument(1L);
        assertTrue(actualDocument.hasBody());
        assertTrue(actualDocument.getHeaders().isEmpty());
        assertEquals(200, actualDocument.getStatusCodeValue());
        verify(documentService).getDocument(Mockito.<Long>any());
    }

    @Test
    void testDelete() {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        doNothing().when(documentRepository).deleteDocument(Mockito.<Long>any());
        ResponseEntity<Boolean> actualDeleteResult = (new DocumentController(
                new DocumentService(documentRepository, new MediaService()))).delete(1L);
        assertTrue(actualDeleteResult.getBody());
        assertEquals(200, actualDeleteResult.getStatusCodeValue());
        assertTrue(actualDeleteResult.getHeaders().isEmpty());
        verify(documentRepository).deleteDocument(Mockito.<Long>any());
    }

    @Test
    void testDelete3() {
        DocumentService documentService = mock(DocumentService.class);
        doNothing().when(documentService).deleteDocument(Mockito.<Long>any());
        ResponseEntity<Boolean> actualDeleteResult = (new DocumentController(documentService)).delete(1L);
        assertTrue(actualDeleteResult.getBody());
        assertEquals(200, actualDeleteResult.getStatusCodeValue());
        assertTrue(actualDeleteResult.getHeaders().isEmpty());
        verify(documentService).deleteDocument(Mockito.<Long>any());
    }
}

