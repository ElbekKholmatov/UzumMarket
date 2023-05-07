package uz.market.uzum.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.market.uzum.domains.Document;
import uz.market.uzum.services.DocumentService;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Document> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(
                documentService.saveDocument(file)
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Document> updateFile(@RequestParam("file") MultipartFile file,
                                               @PathVariable("id") Long id) {
        return ResponseEntity.ok(
                documentService.updateDocument(file, id)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id) {
        return ResponseEntity.ok(
                documentService.getDocument(id).orElseThrow(
                        () -> new RuntimeException("Document not found")
                )
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok(true);
    }
}
