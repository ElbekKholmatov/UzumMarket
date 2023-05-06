package uz.market.uzum.repositories;


import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.market.uzum.domains.Document;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("select d from Document d where d.path = ?1")
    Optional<Document> findByPath(String fileName);

    @Query("select d from Document d where d.createdBy = ?1")
    Page<Document> findAllByCreatedBy(Long id, Pageable pageable);

    @Query("select d from Document d where d.generatedName = ?1")

    Optional<Document> findByName(String fileName);
    @Query("select d from Document d where d.generatedName = ?1")
    Document findByNameLink(String fileName);

//    @Transactional
//    @Modifying
//    @Query("update Document d set d.deleted = true where d.deleted = false and d.id = ?1")
//    void deleteDocument(int id);
}