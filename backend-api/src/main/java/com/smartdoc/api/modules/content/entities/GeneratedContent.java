package com.smartdoc.api.modules.content.entities;


import com.smartdoc.api.modules.content.enums.ContentType;
import com.smartdoc.api.modules.document.entities.Document;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name ="generated_content")
public class GeneratedContent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(columnDefinition = "TEXT")
    private String contentData;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    @ToString.Exclude
    private Document document;
}
