package uz.market.uzum.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.market.uzum.domains.Document;
import uz.market.uzum.repositories.DocumentRepository;

import java.util.Optional;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final MediaService mediaService;

    public Document saveDocument(MultipartFile file) {
        return documentRepository.save(
                Document.childBuilder()
                        .originalName(file.getOriginalFilename())
                        .generatedName(randomUUID() + file.getOriginalFilename())
                        .extension(StringUtils.getFilenameExtension(file.getOriginalFilename()))
                        .mimeType(file.getContentType())
                        .size(file.getSize())
                        .path(mediaService.upload(file))
                        .build()
        );
    }

    @Transactional
    @Modifying
    public Document updateDocument(MultipartFile file, Long id) {
        deleteDocument(id);
        return documentRepository.save(
                Document.childBuilder()
                        .originalName(file.getOriginalFilename())
                        .generatedName(randomUUID() + file.getOriginalFilename())
                        .extension(StringUtils.getFilenameExtension(file.getOriginalFilename()))
                        .mimeType(file.getContentType())
                        .size(file.getSize())
                        .path(mediaService.upload(file))
                        .build()
        );
    }

    public Optional<Document> getDocument(Long id) {
        return documentRepository.findById(id);
    }

    @Async
    public void deleteDocument(Long id) {
        documentRepository.deleteDocument(id);
    }
}
