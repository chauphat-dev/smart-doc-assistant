package com.smartdoc.api.modules.conversation.entities;


import com.smartdoc.api.modules.document.entities.Document;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
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
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if  (o == null) return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        if (this.id==null) return  false;
        Conversation conversation = (Conversation) o;
        return Objects.equals(id, conversation.getId());
    }

    @Override
    public int hashCode() {
        return Hibernate.getClass(this).hashCode();
    }

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
