package com.smartdoc.api.modules.document.mappers;

import com.smartdoc.api.modules.document.entities.Document;
import com.smartdoc.api.modules.document.records.DocumentResponse;

import com.smartdoc.api.modules.document.records.DocumentSummary;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentResponse toDocumentResponse(Document document);

    DocumentSummary toDocumentSummary(Document document);
}
