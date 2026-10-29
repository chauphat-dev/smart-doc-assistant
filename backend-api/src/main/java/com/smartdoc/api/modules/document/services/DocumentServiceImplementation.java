package com.smartdoc.api.modules.document.services;

import com.smartdoc.api.common.exceptions.NotFoundException;
import com.smartdoc.api.modules.document.entities.Document;
import com.smartdoc.api.modules.document.enums.DocumentStatus;
import com.smartdoc.api.modules.document.enums.DocumentType;
import com.smartdoc.api.modules.document.mappers.DocumentMapper;
import com.smartdoc.api.modules.document.records.DocumentResponse;
import com.smartdoc.api.modules.document.records.DocumentSummary;
import com.smartdoc.api.modules.document.repositories.DocumentRepository;
import com.smartdoc.api.modules.document.repositories.specifications.DocumentSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DocumentServiceImplementation implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentServiceImplementation(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public DocumentResponse findById(UUID id) {
        return documentRepository.findById(id)
                .map(documentMapper::toDocumentResponse)
                .orElseThrow(() -> new NotFoundException("Document not found with id: " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DocumentSummary> findAllByStatusAndType(DocumentType documentType, DocumentStatus documentStatus, Pageable pageable){
        Specification<Document> spec= DocumentSpec.filterBy(documentType, documentStatus);
        return documentRepository.findAll(spec, pageable).map(documentMapper::toDocumentSummary);
    };

}