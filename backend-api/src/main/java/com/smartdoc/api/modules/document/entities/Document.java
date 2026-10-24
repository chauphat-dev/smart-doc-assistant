package com.smartdoc.api.modules.document.entities;

import com.smartdoc.api.modules.content.entities.GeneratedContent;
import com.smartdoc.api.modules.conversation.entities.Conversation;
import com.smartdoc.api.modules.document.enums.DocumentStatus;
import com.smartdoc.api.modules.document.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "document")
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType docType;

    private String contentType;

    private Long fileSize;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    private int chunkCount;


    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    private String sourceUrl;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder.Default
    @OneToMany(
            mappedBy = "document",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<DocumentChunk> chunks= new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "documents")
    @ToString.Exclude
    private Set<Conversation> conversations=new HashSet<>();


    
    @Builder.Default
    @OneToMany(mappedBy = "document")
    @ToString.Exclude
    private List<GeneratedContent>  generatedContents=new ArrayList<>();
}
