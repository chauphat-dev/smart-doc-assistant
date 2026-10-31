package com.smartdoc.api.modules.document.records;

import com.smartdoc.api.modules.document.enums.DocumentStatus;
import com.smartdoc.api.modules.document.enums.DocumentType;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentResponse(UUID id,
                               String title,
                               String fileName,
                               DocumentType docType,
                               Long fileSize,
                               DocumentStatus status,
                               int chunkCount,
                               String errorMessage,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {}
