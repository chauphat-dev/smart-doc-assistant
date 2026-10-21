package com.smartdoc.api.modules.conversation.entities;


import com.smartdoc.api.modules.document.entities.Document;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "conversation_documents",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    @ToString.Exclude
    private Set<Document> documents = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "conversation",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<ChatMessage> chatMessages = new ArrayList<>();
}
