package com.smartdoc.api.modules.document.services;

import com.smartdoc.api.modules.document.entities.Document;
import com.smartdoc.api.modules.document.records.DocumentResponse;
import com.smartdoc.api.modules.document.repositories.DocumentRepository;

import java.util.UUID;

public interface DocumentService {
    DocumentResponse findById(UUID id);
}
