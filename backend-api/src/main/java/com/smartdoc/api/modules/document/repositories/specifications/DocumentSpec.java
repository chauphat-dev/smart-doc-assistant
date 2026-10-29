package com.smartdoc.api.modules.document.repositories.specifications;

import com.smartdoc.api.modules.document.entities.Document;
import com.smartdoc.api.modules.document.enums.DocumentStatus;
import com.smartdoc.api.modules.document.enums.DocumentType;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public class DocumentSpec {
    public static Specification<Document> hasDocType(DocumentType docType) {
        return (root, query, cb) -> docType == null ? null : cb.equal(root.get("docType"), docType);
    }

    public static Specification<Document> hasStatus(DocumentStatus status) {
        return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
    }
    public static Specification<Document> filterBy(DocumentType documentType, DocumentStatus documentStatus) {
        return Specification.where(hasDocType(documentType).and(hasStatus(documentStatus)));
    }
}
