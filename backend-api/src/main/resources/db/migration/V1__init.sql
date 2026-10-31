-- 1.  document
CREATE TABLE document (
                          id UUID PRIMARY KEY,
                          title VARCHAR(255) NULL,
                          file_name VARCHAR(255) NOT NULL,
                          file_path VARCHAR(255) NOT NULL,
                          doc_type VARCHAR(255) NOT NULL,
                          file_size BIGINT NULL,
                          status VARCHAR(255) NOT NULL,
                          chunk_count INTEGER NOT NULL,
                          error_message TEXT NULL,
                          content_type VARCHAR(255) NULL,
                          source_url VARCHAR(255) NULL,
                          created_at TIMESTAMP(6) NOT NULL,
                          updated_at TIMESTAMP(6) NULL
);

-- 2.  conversation
CREATE TABLE conversation (
                              id UUID PRIMARY KEY,
                              title VARCHAR(255) NULL,
                              created_at TIMESTAMP(6) NOT NULL,
                              updated_at TIMESTAMP(6) NULL
);

-- 3. document_chunk
CREATE TABLE document_chunk (
                                id UUID PRIMARY KEY,
                                chunk_index INTEGER NOT NULL,
                                page_number INTEGER NOT NULL,
                                vector_id VARCHAR(255) NULL,
                                token_count INTEGER NOT NULL,
                                created_at TIMESTAMP(6) NOT NULL,
                                document_id UUID NOT NULL,
                                CONSTRAINT fk_document_chunk_document
                                    FOREIGN KEY (document_id)
                                        REFERENCES document(id)
                                        ON DELETE CASCADE
);

-- 4. Many-many: conversation_documents
CREATE TABLE conversation_documents (
                                        conversation_id UUID NOT NULL,
                                        document_id UUID NOT NULL,
                                        PRIMARY KEY (conversation_id, document_id),
                                        CONSTRAINT fk_cd_conversation
                                            FOREIGN KEY (conversation_id)
                                                REFERENCES conversation(id)
                                                ON DELETE CASCADE,
                                        CONSTRAINT fk_cd_document
                                            FOREIGN KEY (document_id)
                                                REFERENCES document(id)
                                                ON DELETE CASCADE
);

-- 5. chat_message
CREATE TABLE chat_message (
                              id UUID PRIMARY KEY,
                              role VARCHAR(255) NULL,
                              content TEXT NOT NULL,
                              source TEXT NULL,
                              created_at TIMESTAMP(6) NOT NULL,
                              conversation_id UUID NOT NULL,
                              CONSTRAINT fk_chat_message_conversation
                                  FOREIGN KEY (conversation_id)
                                      REFERENCES conversation(id)
                                      ON DELETE CASCADE
);

-- 6. generated_content
CREATE TABLE generated_content (
                                   id UUID PRIMARY KEY,
                                   title VARCHAR(255) NULL,
                                   content_type VARCHAR(255) NULL,
                                   content_data TEXT NULL,
                                   created_at TIMESTAMP(6) NOT NULL,
                                   document_id UUID NOT NULL,
                                   CONSTRAINT fk_generated_content_document
                                       FOREIGN KEY (document_id)
                                           REFERENCES document(id)
                                           ON DELETE CASCADE
);

CREATE INDEX idx_document_chunk_doc_id ON document_chunk(document_id);
CREATE INDEX idx_chat_message_conv_id ON chat_message(conversation_id);
CREATE INDEX idx_generated_content_doc_id ON generated_content(document_id);
CREATE INDEX idx_cd_document_id ON conversation_documents(document_id);