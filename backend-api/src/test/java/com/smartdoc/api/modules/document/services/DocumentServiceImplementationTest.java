package com.smartdoc.api.modules.document.services;


import com.smartdoc.api.common.exceptions.ConflictException;
import com.smartdoc.api.common.exceptions.NotFoundException;
import com.smartdoc.api.modules.document.entities.Document;
import com.smartdoc.api.modules.document.enums.DocumentStatus;
import com.smartdoc.api.modules.document.mappers.DocumentMapper;
import com.smartdoc.api.modules.document.repositories.DocumentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceImplementationTest {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @InjectMocks
    private DocumentServiceImplementation documentServiceImplementation;

    @Test
    @DisplayName("S1: Should throw ConflictException and never delete when document status is PROCESSING")
    void deleteById_whenDocumentIsProcessing_throwsConflictExceptionAndNeverDeletes() {
        // 1. ARRANGE
        UUID documentId = UUID.randomUUID();
        Document document = new Document();
        document.setId(documentId);
        document.setStatus(DocumentStatus.PROCESSING);

        when(documentRepository.findById(documentId)).thenReturn(Optional.of(document));


        // 2. ACT
        Assertions.assertThrows(ConflictException.class, () -> documentServiceImplementation.deleteById(documentId));

        //3. ASSERT

        verify(documentRepository,never()).delete(any(Document.class));
        verify(documentRepository,never()).deleteById(any());
    }

    @Test
    @DisplayName("S2: Should throw NotFoundException and repository delete is never called")
    void deleteById_whenDocumentIsNotFound_throwsNotFoundException() {
        // 1. ARRANGE
        UUID documentId = UUID.randomUUID();
        when(documentRepository.findById(documentId)).thenReturn(Optional.empty());

        //2. ACT
        Assertions.assertThrows(NotFoundException.class, () -> documentServiceImplementation.deleteById(documentId));

        //3. ASSERT
        verify(documentRepository,never()).delete(any(Document.class));
        verify(documentRepository,never()).deleteById(any());
    }


    @Test
    @DisplayName("S3: Should not throw any exception and delete document with READY status")
    void deleteById_whenDocumentIsReady_deletesSuccessfully() {
        // 1. ARRANGE
        UUID documentId = UUID.randomUUID();
        Document document = new Document();
        document.setId(documentId);
        document.setStatus(DocumentStatus.READY);

        when(documentRepository.findById(documentId)).thenReturn(Optional.of(document));

        // 2. ACT
        Assertions.assertDoesNotThrow(() -> documentServiceImplementation.deleteById(documentId));

        //3. ASSERT
        verify(documentRepository).delete(document);

    }


    @Test
    @DisplayName("S4: Should not throw any exception and delete document with ERROR status")
    void deleteById_whenDocumentIsError_deletesSuccessfully() {
        // 1. ARRANGE
        UUID documentId = UUID.randomUUID();
        Document document = new Document();
        document.setId(documentId);
        document.setStatus(DocumentStatus.ERROR);

        when(documentRepository.findById(documentId)).thenReturn(Optional.of(document));

        // 2. ACT
        Assertions.assertDoesNotThrow(() -> documentServiceImplementation.deleteById(documentId));

        //3. ASSERT
        verify(documentRepository).delete(document);

    }

}
