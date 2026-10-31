package com.smartdoc.api.modules.document.controllers;

import com.smartdoc.api.common.exceptions.NotFoundException;
import com.smartdoc.api.common.web.GlobalExceptionHandler;
import com.smartdoc.api.modules.document.records.DocumentResponse;
import com.smartdoc.api.modules.document.records.DocumentSummary;
import com.smartdoc.api.modules.document.services.DocumentService;
// Import các enum tương ứng trong entity/module document của dự án
import com.smartdoc.api.modules.document.enums.DocumentStatus;
import com.smartdoc.api.modules.document.enums.DocumentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DocumentController.class)
@Import(GlobalExceptionHandler.class)
class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DocumentService documentService;

    private static final String BASE_URL = "/api/v1/documents/{id}";

    @Test
    @DisplayName("GET /api/v1/documents/{id} - 200 OK khi ID hợp lệ và tồn tại")
    void getDocumentById_WhenValidId_ShouldReturn200AndDocument() throws Exception {
        // 1. Arrange - Khởi tạo đúng 10 trường của DocumentResponse record
        UUID validId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        DocumentResponse mockResponse = new DocumentResponse(
                validId,
                "Báo cáo tài chính Q3",
                "financial_report_q3.pdf",
                DocumentType.PDF, // hoặc giá trị enum DocumentType tương ứng
                1048576L,
                DocumentStatus.READY, // hoặc giá trị enum DocumentStatus tương ứng
                12,
                null,
                now,
                now
        );

        when(documentService.findById(validId)).thenReturn(mockResponse);

        // 2. Act & 3. Assert - Chỉ assert các trường thực tế có trong DocumentResponse
        mockMvc.perform(get(BASE_URL, validId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(validId.toString()))
                .andExpect(jsonPath("$.title").value("Báo cáo tài chính Q3"))
                .andExpect(jsonPath("$.fileName").value("financial_report_q3.pdf"))
                .andExpect(jsonPath("$.fileSize").value(1048576))
                .andExpect(jsonPath("$.chunkCount").value(12));

        verify(documentService).findById(validId);
    }

    @Test
    @DisplayName("GET /api/v1/documents/{id} - 404 Not Found khi tài liệu không tồn tại")
    void getDocumentById_WhenNotFound_ShouldReturn404ProblemDetail() throws Exception {
        // 1. Arrange - Dùng đúng constructor 1 tham số String của NotFoundException
        UUID nonExistentId = UUID.randomUUID();
        String expectedMessage = "Document not found with id: " + nonExistentId;

        when(documentService.findById(nonExistentId))
                .thenThrow(new NotFoundException(expectedMessage));

        // 2. Act & 3. Assert - Khớp đúng title và detail từ GlobalExceptionHandler
        mockMvc.perform(get(BASE_URL, nonExistentId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title").value("Resource Not Found"))
                .andExpect(jsonPath("$.detail").value(expectedMessage));

        verify(documentService).findById(nonExistentId);
    }

    @Test
    @DisplayName("GET /api/v1/documents/{id} - 400 Bad Request khi UUID sai định dạng")
    void getDocumentById_WhenInvalidUUIDFormat_ShouldReturn400ProblemDetail() throws Exception {
        // 1. Arrange
        String invalidId = "not-a-valid-uuid";

        // 2. Act & 3. Assert - Khớp title với MethodArgumentTypeMismatch trong GlobalExceptionHandler
        mockMvc.perform(get(BASE_URL, invalidId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.title").value("Method Argument Type Mismatch"));

        verifyNoInteractions(documentService);
    }


    @Test
    @DisplayName("GET /api/v1/documents - 200 OK và trả về danh sách phân trang thành công")
    void getDocuments_ShouldReturnDocumentPage() throws Exception {
        // 1. Arrange
        UUID docId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        // Giả lập 1 bản ghi DocumentSummary (điều chỉnh các trường theo đúng record của dự án bạn)
        DocumentSummary summary = new DocumentSummary(
                docId,
                "Báo cáo tài chính Q3",
                "financial_report_q3.pdf",
                DocumentType.PDF,
                1048576L,
                DocumentStatus.READY,
                12,
                now,
                now
        );

        // Tạo đối tượng Page giả lập trả về từ Service
        Page<DocumentSummary> mockPage = new PageImpl<>(
                List.of(summary),
                PageRequest.of(0, 10),
                1L
        );

        // Mock Service: chấp nhận mọi tham số lọc và phân trang truyền vào
        when(documentService.findAllByStatusAndType(any(), any(), any(Pageable.class)))
                .thenReturn(mockPage);

        // 2. Act & 3. Assert
        mockMvc.perform(get("/api/v1/documents")
                        .param("docType", "PDF")
                        .param("status", "READY")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Kiểm tra mảng nội dung bên trong Page (mặc định Spring Data trả về trường "content")
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value(docId.toString()))
                .andExpect(jsonPath("$.content[0].title").value("Báo cáo tài chính Q3"))
                .andExpect(jsonPath("$.content[0].fileName").value("financial_report_q3.pdf"))
                // Kiểm tra metadata phân trang
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.number").value(0));

        // Xác minh service được gọi đúng 1 lần
        verify(documentService).findAllByStatusAndType(any(), any(), any(Pageable.class));
    }
}