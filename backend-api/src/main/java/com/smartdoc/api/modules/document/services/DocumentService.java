package com.smartdoc.api.modules.document.services;

import com.smartdoc.api.modules.document.entities.Document;
import com.smartdoc.api.modules.document.enums.DocumentStatus;
import com.smartdoc.api.modules.document.enums.DocumentType;
import com.smartdoc.api.modules.document.records.DocumentResponse;
import com.smartdoc.api.modules.document.records.DocumentSummary;
import com.smartdoc.api.modules.document.repositories.DocumentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DocumentService {

    //Core CRUD
    DocumentResponse findById(UUID id);
    void deleteById(UUID id);

    Page<DocumentSummary> findAllByStatusAndType(DocumentType documentType, DocumentStatus documentStatus, Pageable pageable);

}
