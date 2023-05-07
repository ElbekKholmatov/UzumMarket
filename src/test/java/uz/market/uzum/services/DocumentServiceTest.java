package uz.market.uzum.services;

import static org.junit.jupiter.api.Assertions.assertSame;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import uz.market.uzum.domains.Document;
import uz.market.uzum.repositories.DocumentRepository;

class DocumentServiceTest {
    @Test
    void testSaveDocument() throws IOException {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        DocumentService documentService = new DocumentService(documentRepository, new MediaService());
        documentService.saveDocument(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }
    @Test
    void testSaveDocument2() throws IOException {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        Document document = new Document();
        when(documentRepository.save(Mockito.<Document>any())).thenReturn(document);
        MediaService mediaService = mock(MediaService.class);
        when(mediaService.upload(Mockito.<MultipartFile>any())).thenReturn("Upload");
        DocumentService documentService = new DocumentService(documentRepository, mediaService);
        assertSame(document, documentService
                .saveDocument(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(documentRepository).save(Mockito.<Document>any());
        verify(mediaService).upload(Mockito.<MultipartFile>any());
    }

    @Test
    void testUpdateDocument() throws IOException {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        doNothing().when(documentRepository).deleteDocument(Mockito.<Long>any());
        DocumentService documentService = new DocumentService(documentRepository, new MediaService());
        documentService
                .updateDocument(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), 1L);
    }

    @Test
    void testUpdateDocument2() throws IOException {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        Document document = new Document();
        when(documentRepository.save(Mockito.<Document>any())).thenReturn(document);
        doNothing().when(documentRepository).deleteDocument(Mockito.<Long>any());
        MediaService mediaService = mock(MediaService.class);
        when(mediaService.upload(Mockito.<MultipartFile>any())).thenReturn("Upload");
        DocumentService documentService = new DocumentService(documentRepository, mediaService);
        assertSame(document, documentService
                .updateDocument(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), 1L));
        verify(documentRepository).save(Mockito.<Document>any());
        verify(documentRepository).deleteDocument(Mockito.<Long>any());
        verify(mediaService).upload(Mockito.<MultipartFile>any());
    }

    @Test
    void testGetDocument() {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        Optional<Document> ofResult = Optional.of(new Document());
        when(documentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<Document> actualDocument = (new DocumentService(documentRepository, new MediaService())).getDocument(1L);
        assertSame(ofResult, actualDocument);
        assertTrue(actualDocument.isPresent());
        verify(documentRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testDeleteDocument() {
        DocumentRepository documentRepository = mock(DocumentRepository.class);
        doNothing().when(documentRepository).deleteDocument(Mockito.<Long>any());
        (new DocumentService(documentRepository, new MediaService())).deleteDocument(1L);
        verify(documentRepository).deleteDocument(Mockito.<Long>any());
    }
}

