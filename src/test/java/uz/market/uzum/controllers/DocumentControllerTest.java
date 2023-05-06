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
    @Test
    @Disabled("TODO: Complete this test")
    void testUploadFile() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.cloud.storage.StorageException: com.diffblue.cover.sandbox.execution.ForbiddenByPolicyException: Sandboxing policy violation. Reason: to access the network
        //       at com.google.cloud.storage.StorageException.getStorageException(StorageException.java:99)
        //       at com.google.cloud.storage.StorageException.coalesce(StorageException.java:119)
        //       at com.google.cloud.storage.Retrying.run(Retrying.java:63)
        //       at com.google.cloud.storage.StorageImpl.run(StorageImpl.java:1478)
        //       at com.google.cloud.storage.StorageImpl.internalCreate(StorageImpl.java:207)
        //       at com.google.cloud.storage.StorageImpl.create(StorageImpl.java:153)
        //       at uz.market.uzum.services.MediaService.upload(MediaService.java:33)
        //       at uz.market.uzum.services.DocumentService.saveDocument(DocumentService.java:29)
        //       at uz.market.uzum.controllers.DocumentController.uploadFile(DocumentController.java:22)
        //   com.diffblue.cover.sandbox.execution.ForbiddenByPolicyException: Sandboxing policy violation. Reason: to access the network
        //       at java.lang.SecurityManager.checkConnect(SecurityManager.java:916)
        //       at java.net.InetAddress.getAllByName0(InetAddress.java:1481)
        //       at java.net.InetAddress.getAllByName(InetAddress.java:1385)
        //       at java.net.InetAddress.getAllByName(InetAddress.java:1306)
        //       at java.net.InetAddress.getByName(InetAddress.java:1256)
        //       at sun.net.www.protocol.http.HttpURLConnection$5.run(HttpURLConnection.java:1084)
        //       at sun.net.www.protocol.http.HttpURLConnection$5.run(HttpURLConnection.java:1082)
        //       at java.security.AccessController.doPrivileged(AccessController.java:569)
        //       at sun.net.www.protocol.http.HttpURLConnection.getHostAndPort(HttpURLConnection.java:1081)
        //       at sun.net.www.protocol.http.HttpURLConnection.URLtoSocketPermission(HttpURLConnection.java:1154)
        //       at sun.net.www.protocol.http.HttpURLConnection.getOutputStream(HttpURLConnection.java:1386)
        //       at sun.net.www.protocol.https.HttpsURLConnectionImpl.getOutputStream(HttpsURLConnectionImpl.java:220)
        //       at com.google.api.client.http.javanet.NetHttpRequest.execute(NetHttpRequest.java:113)
        //       at com.google.api.client.http.javanet.NetHttpRequest.execute(NetHttpRequest.java:84)
        //       at com.google.api.client.http.HttpRequest.execute(HttpRequest.java:1012)
        //       at com.google.auth.oauth2.ServiceAccountCredentials.refreshAccessToken(ServiceAccountCredentials.java:564)
        //       at com.google.auth.oauth2.OAuth2Credentials$1.call(OAuth2Credentials.java:257)
        //       at com.google.auth.oauth2.OAuth2Credentials$1.call(OAuth2Credentials.java:254)
        //       at java.util.concurrent.FutureTask.run(FutureTask.java:264)
        //       at com.google.auth.oauth2.OAuth2Credentials$RefreshTask.run(OAuth2Credentials.java:623)
        //       at com.google.common.util.concurrent.DirectExecutor.execute(DirectExecutor.java:31)
        //       at com.google.auth.oauth2.OAuth2Credentials$AsyncRefreshResult.executeIfNew(OAuth2Credentials.java:571)
        //       at com.google.auth.oauth2.OAuth2Credentials.asyncFetch(OAuth2Credentials.java:220)
        //       at com.google.auth.oauth2.OAuth2Credentials.getRequestMetadata(OAuth2Credentials.java:170)
        //       at com.google.auth.oauth2.ServiceAccountCredentials.getRequestMetadata(ServiceAccountCredentials.java:968)
        //       at com.google.auth.http.HttpCredentialsAdapter.initialize(HttpCredentialsAdapter.java:96)
        //       at com.google.cloud.http.HttpTransportOptions$1.initialize(HttpTransportOptions.java:159)
        //       at com.google.cloud.http.CensusHttpModule$CensusHttpRequestInitializer.initialize(CensusHttpModule.java:109)
        //       at com.google.cloud.storage.spi.v1.HttpStorageRpc$InvocationIdInitializer.initialize(HttpStorageRpc.java:146)
        //       at com.google.api.client.http.HttpRequestFactory.buildRequest(HttpRequestFactory.java:91)
        //       at com.google.api.client.googleapis.services.AbstractGoogleClientRequest.executeUnparsed(AbstractGoogleClientRequest.java:532)
        //       at com.google.api.client.googleapis.services.AbstractGoogleClientRequest.executeUnparsed(AbstractGoogleClientRequest.java:466)
        //       at com.google.api.client.googleapis.services.AbstractGoogleClientRequest.execute(AbstractGoogleClientRequest.java:576)
        //       at com.google.cloud.storage.spi.v1.HttpStorageRpc.create(HttpStorageRpc.java:370)
        //       at com.google.cloud.storage.StorageImpl.lambda$internalCreate$2(StorageImpl.java:210)
        //       at com.google.api.gax.retrying.DirectRetryingExecutor.submit(DirectRetryingExecutor.java:103)
        //       at com.google.cloud.RetryHelper.run(RetryHelper.java:76)
        //       at com.google.cloud.RetryHelper.runWithRetries(RetryHelper.java:50)
        //       at com.google.cloud.storage.Retrying.run(Retrying.java:60)
        //       at com.google.cloud.storage.StorageImpl.run(StorageImpl.java:1478)
        //       at com.google.cloud.storage.StorageImpl.internalCreate(StorageImpl.java:207)
        //       at com.google.cloud.storage.StorageImpl.create(StorageImpl.java:153)
        //       at uz.market.uzum.services.MediaService.upload(MediaService.java:33)
        //       at uz.market.uzum.services.DocumentService.saveDocument(DocumentService.java:29)
        //       at uz.market.uzum.controllers.DocumentController.uploadFile(DocumentController.java:22)
        //   com.diffblue.cover.exception.BaseException: Sandboxing policy violation. Reason: to access the network
        //       at java.lang.SecurityManager.checkConnect(SecurityManager.java:916)
        //       at java.net.InetAddress.getAllByName0(InetAddress.java:1481)
        //       at java.net.InetAddress.getAllByName(InetAddress.java:1385)
        //       at java.net.InetAddress.getAllByName(InetAddress.java:1306)
        //       at java.net.InetAddress.getByName(InetAddress.java:1256)
        //       at sun.net.www.protocol.http.HttpURLConnection$5.run(HttpURLConnection.java:1084)
        //       at sun.net.www.protocol.http.HttpURLConnection$5.run(HttpURLConnection.java:1082)
        //       at java.security.AccessController.doPrivileged(AccessController.java:569)
        //       at sun.net.www.protocol.http.HttpURLConnection.getHostAndPort(HttpURLConnection.java:1081)
        //       at sun.net.www.protocol.http.HttpURLConnection.URLtoSocketPermission(HttpURLConnection.java:1154)
        //       at sun.net.www.protocol.http.HttpURLConnection.getOutputStream(HttpURLConnection.java:1386)
        //       at sun.net.www.protocol.https.HttpsURLConnectionImpl.getOutputStream(HttpsURLConnectionImpl.java:220)
        //       at com.google.api.client.http.javanet.NetHttpRequest.execute(NetHttpRequest.java:113)
        //       at com.google.api.client.http.javanet.NetHttpRequest.execute(NetHttpRequest.java:84)
        //       at com.google.api.client.http.HttpRequest.execute(HttpRequest.java:1012)
        //       at com.google.auth.oauth2.ServiceAccountCredentials.refreshAccessToken(ServiceAccountCredentials.java:564)
        //       at com.google.auth.oauth2.OAuth2Credentials$1.call(OAuth2Credentials.java:257)
        //       at com.google.auth.oauth2.OAuth2Credentials$1.call(OAuth2Credentials.java:254)
        //       at java.util.concurrent.FutureTask.run(FutureTask.java:264)
        //       at com.google.auth.oauth2.OAuth2Credentials$RefreshTask.run(OAuth2Credentials.java:623)
        //       at com.google.common.util.concurrent.DirectExecutor.execute(DirectExecutor.java:31)
        //       at com.google.auth.oauth2.OAuth2Credentials$AsyncRefreshResult.executeIfNew(OAuth2Credentials.java:571)
        //       at com.google.auth.oauth2.OAuth2Credentials.asyncFetch(OAuth2Credentials.java:220)
        //       at com.google.auth.oauth2.OAuth2Credentials.getRequestMetadata(OAuth2Credentials.java:170)
        //       at com.google.auth.oauth2.ServiceAccountCredentials.getRequestMetadata(ServiceAccountCredentials.java:968)
        //       at com.google.auth.http.HttpCredentialsAdapter.initialize(HttpCredentialsAdapter.java:96)
        //       at com.google.cloud.http.HttpTransportOptions$1.initialize(HttpTransportOptions.java:159)
        //       at com.google.cloud.http.CensusHttpModule$CensusHttpRequestInitializer.initialize(CensusHttpModule.java:109)
        //       at com.google.cloud.storage.spi.v1.HttpStorageRpc$InvocationIdInitializer.initialize(HttpStorageRpc.java:146)
        //       at com.google.api.client.http.HttpRequestFactory.buildRequest(HttpRequestFactory.java:91)
        //       at com.google.api.client.googleapis.services.AbstractGoogleClientRequest.executeUnparsed(AbstractGoogleClientRequest.java:532)
        //       at com.google.api.client.googleapis.services.AbstractGoogleClientRequest.executeUnparsed(AbstractGoogleClientRequest.java:466)
        //       at com.google.api.client.googleapis.services.AbstractGoogleClientRequest.execute(AbstractGoogleClientRequest.java:576)
        //       at com.google.cloud.storage.spi.v1.HttpStorageRpc.create(HttpStorageRpc.java:370)
        //       at com.google.cloud.storage.StorageImpl.lambda$internalCreate$2(StorageImpl.java:210)
        //       at com.google.api.gax.retrying.DirectRetryingExecutor.submit(DirectRetryingExecutor.java:103)
        //       at com.google.cloud.RetryHelper.run(RetryHelper.java:76)
        //       at com.google.cloud.RetryHelper.runWithRetries(RetryHelper.java:50)
        //       at com.google.cloud.storage.Retrying.run(Retrying.java:60)
        //       at com.google.cloud.storage.StorageImpl.run(StorageImpl.java:1478)
        //       at com.google.cloud.storage.StorageImpl.internalCreate(StorageImpl.java:207)
        //       at com.google.cloud.storage.StorageImpl.create(StorageImpl.java:153)
        //       at uz.market.uzum.services.MediaService.upload(MediaService.java:33)
        //       at uz.market.uzum.services.DocumentService.saveDocument(DocumentService.java:29)
        //       at uz.market.uzum.controllers.DocumentController.uploadFile(DocumentController.java:22)
        //   See https://diff.blue/R013 to resolve this issue.

        DocumentRepository documentRepository = mock(DocumentRepository.class);
        DocumentController documentController = new DocumentController(
                new DocumentService(documentRepository, new MediaService()));
        documentController
                .uploadFile(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link DocumentController#uploadFile(MultipartFile)}
     */
    @Test
    void testUploadFile2() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

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
    @Disabled("TODO: Complete this test")
    void testGetDocument3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "uz.market.uzum.services.DocumentService.getDocument(java.lang.Long)" because "this.documentService" is null
        //       at uz.market.uzum.controllers.DocumentController.getDocument(DocumentController.java:29)
        //   See https://diff.blue/R013 to resolve this issue.

        (new DocumentController(null)).getDocument(1L);
    }

    /**
     * Method under test: {@link DocumentController#getDocument(Long)}
     */
    @Test
    void testGetDocument4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

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
    @Disabled("TODO: Complete this test")
    void testDelete2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "uz.market.uzum.services.DocumentService.deleteDocument(java.lang.Long)" because "this.documentService" is null
        //       at uz.market.uzum.controllers.DocumentController.delete(DocumentController.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        (new DocumentController(null)).delete(1L);
    }

    /**
     * Method under test: {@link DocumentController#delete(Long)}
     */
    @Test
    void testDelete3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        DocumentService documentService = mock(DocumentService.class);
        doNothing().when(documentService).deleteDocument(Mockito.<Long>any());
        ResponseEntity<Boolean> actualDeleteResult = (new DocumentController(documentService)).delete(1L);
        assertTrue(actualDeleteResult.getBody());
        assertEquals(200, actualDeleteResult.getStatusCodeValue());
        assertTrue(actualDeleteResult.getHeaders().isEmpty());
        verify(documentService).deleteDocument(Mockito.<Long>any());
    }
}

