package com.example.patterns.structural.composite;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for Composite pattern implementation.
 */
@DisplayName("Composite Pattern Tests")
class FileSystemCompositeTest {
    
    @Nested
    @DisplayName("File (Leaf) Tests")
    class FileTests {
        
        @Test
        @DisplayName("Should create file with correct properties")
        void shouldCreateFile_withCorrectProperties() {
            // Act
            File file = new File("test.txt", 1024, "/home/user");
            
            // Assert
            assertThat(file.getName()).isEqualTo("test.txt");
            assertThat(file.getPath()).isEqualTo("/home/user/test.txt");
            assertThat(file.getSize()).isEqualTo(1024);
        }
        
        @Test
        @DisplayName("File should not support add operation")
        void file_shouldNotSupportAddOperation() {
            // Arrange
            File file = new File("test.txt", 1024, "/home/user");
            File childFile = new File("child.txt", 512, "/home/user");
            
            // Act & Assert
            assertThatThrownBy(() -> file.add(childFile))
                .isInstanceOf(UnsupportedOperationException.class);
        }
        
        @Test
        @DisplayName("File should not support remove operation")
        void file_shouldNotSupportRemoveOperation() {
            // Arrange
            File file = new File("test.txt", 1024, "/home/user");
            File childFile = new File("child.txt", 512, "/home/user");
            
            // Act & Assert
            assertThatThrownBy(() -> file.remove(childFile))
                .isInstanceOf(UnsupportedOperationException.class);
        }
    }
    
    @Nested
    @DisplayName("Directory (Composite) Tests")
    class DirectoryTests {
        
        @Test
        @DisplayName("Should create empty directory")
        void shouldCreateEmptyDirectory() {
            // Act
            Directory directory = new Directory("docs", "/home/user");
            
            // Assert
            assertThat(directory.getName()).isEqualTo("docs");
            assertThat(directory.getPath()).isEqualTo("/home/user/docs");
            assertThat(directory.getSize()).isEqualTo(0);
            assertThat(directory.getChildren()).isEmpty();
        }
        
        @Test
        @DisplayName("Should add file to directory")
        void shouldAddFile_toDirectory() {
            // Arrange
            Directory directory = new Directory("docs", "/home/user");
            File file = new File("readme.txt", 1024, "/home/user/docs");
            
            // Act
            directory.add(file);
            
            // Assert
            assertThat(directory.getChildren()).hasSize(1).contains(file);
            assertThat(directory.getSize()).isEqualTo(1024);
        }
        
        @Test
        @DisplayName("Should remove file from directory")
        void shouldRemoveFile_fromDirectory() {
            // Arrange
            Directory directory = new Directory("docs", "/home/user");
            File file = new File("readme.txt", 1024, "/home/user/docs");
            directory.add(file);
            
            // Act
            boolean removed = directory.remove(file);
            
            // Assert
            assertThat(removed).isTrue();
            assertThat(directory.getChildren()).isEmpty();
            assertThat(directory.getSize()).isEqualTo(0);
        }
        
        @Test
        @DisplayName("Should throw exception when adding null component")
        void shouldThrowException_whenAddingNullComponent() {
            // Arrange
            Directory directory = new Directory("docs", "/home/user");
            
            // Act & Assert
            assertThatThrownBy(() -> directory.add(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Component cannot be null");
        }
    }
    
    @Nested
    @DisplayName("Nested Directory Tests")
    class NestedDirectoryTests {
        
        @Test
        @DisplayName("Should support nested directories")
        void shouldSupportNestedDirectories() {
            // Arrange
            Directory root = new Directory("root", "/");
            Directory subDir1 = new Directory("docs", "/root");
            Directory subDir2 = new Directory("images", "/root");
            
            // Act
            root.add(subDir1);
            root.add(subDir2);
            
            // Assert
            assertThat(root.getChildren()).hasSize(2);
            assertThat(root.getChildren()).contains(subDir1, subDir2);
        }
        
        @Test
        @DisplayName("Should calculate total size recursively")
        void shouldCalculateTotalSize_recursively() {
            // Arrange
            Directory root = new Directory("root", "/");
            Directory docs = new Directory("docs", "/root");
            Directory images = new Directory("images", "/root");
            
            File readme = new File("readme.txt", 1024, "/root/docs");
            File guide = new File("guide.txt", 2048, "/root/docs");
            File photo = new File("photo.jpg", 4096, "/root/images");
            
            docs.add(readme);
            docs.add(guide);
            images.add(photo);
            root.add(docs);
            root.add(images);
            
            // Act
            long totalSize = root.getSize();
            
            // Assert
            assertThat(totalSize).isEqualTo(1024 + 2048 + 4096);
        }
        
        @Test
        @DisplayName("Should display tree structure correctly")
        void shouldDisplayTreeStructure_correctly() {
            // Arrange
            Directory root = new Directory("root", "/");
            Directory docs = new Directory("docs", "/root");
            File readme = new File("readme.txt", 1024, "/root/docs");
            
            docs.add(readme);
            root.add(docs);
            
            // Act
            String display = captureDisplay(root);
            
            // Assert
            assertThat(display)
                .contains("[DIR] root")
                .contains("[DIR] docs")
                .contains("[FILE] readme.txt (1024 bytes)");
        }
    }
    
    @Nested
    @DisplayName("Uniform Treatment Tests")
    class UniformTreatmentTests {
        
        @Test
        @DisplayName("Should treat files and directories uniformly")
        void shouldTreatFilesAndDirectories_uniformly() {
            // Arrange
            FileSystemComponent file = new File("test.txt", 1024, "/home/user");
            FileSystemComponent directory = new Directory("docs", "/home/user");
            
            // Act & Assert
            assertThat(file.getName()).isNotEmpty();
            assertThat(file.getPath()).isNotEmpty();
            assertThat(file.getSize()).isGreaterThanOrEqualTo(0);
            
            assertThat(directory.getName()).isNotEmpty();
            assertThat(directory.getPath()).isNotEmpty();
            assertThat(directory.getSize()).isGreaterThanOrEqualTo(0);
        }
    }
    
    private String captureDisplay(FileSystemComponent component) {
        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(output));
        
        try {
            component.display("");
            return output.toString();
        } finally {
            System.setOut(originalOut);
        }
    }
}
