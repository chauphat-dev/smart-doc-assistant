package com.smartdoc.api.modules.document.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "document_chunk")
public class DocumentChunk {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int chunkIndex;
    private int pageNumber;
    private String vectorId;
    private int tokenCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if  (o == null) return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        if (this.id==null) return  false;
        DocumentChunk chunk = (DocumentChunk) o;
        return Objects.equals(id, chunk.getId());
    }

    @Override
    public int hashCode() {
        return Hibernate.getClass(this).hashCode();
    }

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="document_id",nullable = false)
    @ToString.Exclude
    private Document document;

}
