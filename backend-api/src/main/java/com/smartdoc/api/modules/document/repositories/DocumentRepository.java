package com.smartdoc.api.modules.document.repositories;

import com.smartdoc.api.modules.document.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
}