package com.smartdoc.api.modules.document.controllers;


import com.smartdoc.api.modules.document.enums.DocumentStatus;
import com.smartdoc.api.modules.document.enums.DocumentType;
import com.smartdoc.api.modules.document.records.DocumentResponse;
import com.smartdoc.api.modules.document.records.DocumentSummary;
import com.smartdoc.api.modules.document.repositories.DocumentRepository;
import com.smartdoc.api.modules.document.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getDocumentById(@PathVariable UUID id) {
        return ResponseEntity.ok(documentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<DocumentSummary>> getDocsByStatusAndType(
            @RequestParam(required = false) DocumentStatus status,
            @RequestParam(required = false)DocumentType docType,
            @PageableDefault(sort = "createdAt",direction = Sort.Direction.DESC) Pageable pageable
            ) {
        return ResponseEntity.ok(documentService.findAllByStatusAndType(docType,status, pageable));
    }

}

