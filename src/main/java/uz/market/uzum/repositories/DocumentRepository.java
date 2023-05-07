package uz.market.uzum.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.market.uzum.domains.Document;

import java.util.Optional;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("select d from Document d where d.isDeleted = false and d.path = ?1")
    Optional<Document> findByPath(String fileName);

    @Query("select d from Document d where d.isDeleted = false and d.createdBy = ?1")
    Page<Document> findAllByCreatedBy(Long id, Pageable pageable);

    @Query("select d from Document d where d.isDeleted = false and d.generatedName = ?1")

    Optional<Document> findByName(String fileName);
    @Query("select d from Document d where d.isDeleted = false and d.generatedName = ?1")
    Document findByNameLink(String fileName);

    @Transactional
    @Modifying
    @Query("update Document d set d.isDeleted = true where d.isDeleted = false and d.id = ?1")
    void deleteDocument(Long id);
}