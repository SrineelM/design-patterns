package com.example.patterns.creational.prototype;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for the Prototype pattern implementation.
 * 
 * Tests verify:
 * - Deep cloning works correctly
 * - Clones are independent from originals
 * - Prototype registry functions properly
 * 
 * @author Design Patterns Team
 */
@DisplayName("Prototype Pattern - Document Tests")
class WordDocumentTest {
    
    @BeforeEach
    void setUp() {
        DocumentPrototypeRegistry.clearRegistry();
    }
    
    @Nested
    @DisplayName("Document Cloning Tests")
    class DocumentCloningTests {
        
        @Test
        @DisplayName("Should create independent clone of document")
        void shouldCreateIndependentClone_ofDocument() throws CloneNotSupportedException {
            // Arrange
            WordDocument original = new WordDocument();
            original.setContent("Original content");
            original.setAuthor("John Doe");
            
            // Act
            Document clone = original.clone();
            
            // Assert
            assertThat(clone).isNotNull();
            assertThat(clone).isNotSameAs(original);
            assertThat(clone.getContent()).isEqualTo("Original content");
        }
        
        @Test
        @DisplayName("Should not affect original when modifying clone")
        void shouldNotAffectOriginal_whenModifyingClone() throws CloneNotSupportedException {
            // Arrange
            WordDocument original = new WordDocument();
            original.setContent("Original content");
            
            // Act
            WordDocument clone = (WordDocument) original.clone();
            clone.setContent("Modified content");
            
            // Assert
            assertThat(original.getContent()).isEqualTo("Original content");
            assertThat(clone.getContent()).isEqualTo("Modified content");
        }
        
        @Test
        @DisplayName("Should reset revision count on clone")
        void shouldResetRevisionCount_onClone() throws CloneNotSupportedException {
            // Arrange
            WordDocument original = new WordDocument();
            original.setContent("Content 1");
            original.setContent("Content 2");
            original.setContent("Content 3");
            
            // Act
            WordDocument clone = (WordDocument) original.clone();
            
            // Assert
            assertThat(original.getRevisionCount()).isEqualTo(3);
            assertThat(clone.getRevisionCount()).isEqualTo(0);
        }
        
        @Test
        @DisplayName("Should clone metadata independently")
        void shouldCloneMetadata_independently() throws CloneNotSupportedException {
            // Arrange
            WordDocument original = new WordDocument();
            original.setMetadata("project", "Design Patterns");
            original.setMetadata("version", "1.0");
            
            // Act
            WordDocument clone = (WordDocument) original.clone();
            clone.setMetadata("version", "2.0");
            
            // Assert
            assertThat(original.getMetadata("version")).isEqualTo("1.0");
            assertThat(clone.getMetadata("version")).isEqualTo("2.0");
        }
    }
    
    @Nested
    @DisplayName("Prototype Registry Tests")
    class PrototypeRegistryTests {
        
        @Test
        @DisplayName("Should register prototype successfully")
        void shouldRegisterPrototype_successfully() {
            // Arrange
            WordDocument template = new WordDocument();
            template.setContent("Dear Sir/Madam,\\n\\nBest regards");
            
            // Act
            DocumentPrototypeRegistry.registerPrototype("businessLetter", template);
            
            // Assert
            assertThat(DocumentPrototypeRegistry.getPrototypeCount()).isEqualTo(1);
            assertThat(DocumentPrototypeRegistry.getPrototypeNames()).contains("businessLetter");
        }
        
        @Test
        @DisplayName("Should create document from registry")
        void shouldCreateDocument_fromRegistry() {
            // Arrange
            WordDocument template = new WordDocument();
            template.setContent("Template content");
            DocumentPrototypeRegistry.registerPrototype("template1", template);
            
            // Act
            Document newDoc = DocumentPrototypeRegistry.createDocument("template1");
            
            // Assert
            assertThat(newDoc).isNotNull();
            assertThat(newDoc).isNotSameAs(template);
            assertThat(newDoc.getContent()).isEqualTo("Template content");
        }
        
        @Test
        @DisplayName("Should throw exception for unknown prototype")
        void shouldThrowException_forUnknownPrototype() {
            // Act & Assert
            assertThatThrownBy(() -> DocumentPrototypeRegistry.createDocument("unknown"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Prototype not found");
        }
        
        @Test
        @DisplayName("Should throw exception for null prototype name")
        void shouldThrowException_forNullPrototypeName() {
            // Arrange
            WordDocument template = new WordDocument();
            
            // Act & Assert
            assertThatThrownBy(() -> DocumentPrototypeRegistry.registerPrototype(null, template))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Should throw exception for null prototype")
        void shouldThrowException_forNullPrototype() {
            // Act & Assert
            assertThatThrownBy(() -> DocumentPrototypeRegistry.registerPrototype("test", null))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Should clear registry")
        void shouldClearRegistry() {
            // Arrange
            WordDocument template = new WordDocument();
            DocumentPrototypeRegistry.registerPrototype("test1", template);
            DocumentPrototypeRegistry.registerPrototype("test2", template);
            
            // Act
            DocumentPrototypeRegistry.clearRegistry();
            
            // Assert
            assertThat(DocumentPrototypeRegistry.getPrototypeCount()).isZero();
        }
        
        @Test
        @DisplayName("Should create multiple independent clones")
        void shouldCreateMultipleIndependentClones() {
            // Arrange
            WordDocument template = new WordDocument();
            template.setContent("Template");
            DocumentPrototypeRegistry.registerPrototype("template", template);
            
            // Act
            Document doc1 = DocumentPrototypeRegistry.createDocument("template");
            Document doc2 = DocumentPrototypeRegistry.createDocument("template");
            doc1.setContent("Document 1");
            doc2.setContent("Document 2");
            
            // Assert
            assertThat(doc1.getContent()).isEqualTo("Document 1");
            assertThat(doc2.getContent()).isEqualTo("Document 2");
            assertThat(template.getContent()).isEqualTo("Template");
        }
    }
}
