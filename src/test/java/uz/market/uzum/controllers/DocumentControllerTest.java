package uz.market.uzum.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import uz.market.uzum.domains.Document;
import uz.market.uzum.repositories.DocumentRepository;
import uz.market.uzum.services.DocumentService;
import uz.market.uzum.services.MediaService;

class DocumentControllerTest {
    /**
     * Method under test: {@link DocumentController#uploadFile(MultipartFile)}
     */

    /**
     * Method under test: {@link DocumentController#uploadFile(MultipartFile)}
     */
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

    /**
     * Method under test: {@link DocumentController#uploadFile(MultipartFile)}
     */
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

    /**
     * Method under test: {@link DocumentController#getDocument(Long)}
     */
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

    /**
     * Method under test: {@link DocumentController#getDocument(Long)}
     */
    @Test
    void testGetDocument2() {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        when(documentRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> (new DocumentController(new DocumentService(documentRepository, new MediaService()))).getDocument(1L));
        verify(documentRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link DocumentController#getDocument(Long)}
     */
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

    /**
     * Method under test: {@link DocumentController#delete(Long)}
     */
    @Test
    void testDelete() {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        doNothing().when(documentRepository).deleteById(Mockito.<Long>any());
        ResponseEntity<Boolean> actualDeleteResult = (new DocumentController(
                new DocumentService(documentRepository, new MediaService()))).delete(1L);
        assertTrue(actualDeleteResult.getBody());
        assertEquals(200, actualDeleteResult.getStatusCodeValue());
        assertTrue(actualDeleteResult.getHeaders().isEmpty());
        verify(documentRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link DocumentController#delete(Long)}
     */
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

