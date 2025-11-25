package com.example.patterns.structural.proxy;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for Proxy pattern implementation.
 */
@DisplayName("Proxy Pattern Tests")
class ImageProxyTest {
    
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    void setUpOutputStream() {
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterEach
    void restoreOutputStream() {
        System.setOut(originalOut);
    }
    
    @Nested
    @DisplayName("Lazy Loading Tests")
    class LazyLoadingTests {
        
        @Test
        @DisplayName("Should not load image when proxy is created")
        void shouldNotLoadImage_whenProxyCreated() {
            // Act
            Image proxy = new ImageProxy("test.jpg");
            String output = outputStream.toString();
            
            // Assert
            assertThat(output)
                .contains("[PROXY] Created proxy")
                .doesNotContain("Loading image from disk");
        }
        
        @Test
        @DisplayName("Should load image on first display call")
        void shouldLoadImage_onFirstDisplayCall() {
            // Arrange
            Image proxy = new ImageProxy("test.jpg");
            outputStream.reset(); // Clear creation output
            
            // Act
            proxy.display();
            String output = outputStream.toString();
            
            // Assert
            assertThat(output)
                .contains("[PROXY] First display() call - loading real image now")
                .contains("Loading image from disk");
        }
        
        @Test
        @DisplayName("Should not reload image on subsequent display calls")
        void shouldNotReloadImage_onSubsequentDisplayCalls() {
            // Arrange
            Image proxy = new ImageProxy("test.jpg");
            proxy.display(); // First call loads
            outputStream.reset();
            
            // Act
            proxy.display(); // Second call should not reload
            String output = outputStream.toString();
            
            // Assert
            assertThat(output)
                .contains("[PROXY] Using cached real image")
                .doesNotContain("Loading image from disk");
        }
    }
    
    @Nested
    @DisplayName("Get Info Tests")
    class GetInfoTests {
        
        @Test
        @DisplayName("Should return info without loading image")
        void shouldReturnInfo_withoutLoadingImage() {
            // Arrange
            Image proxy = new ImageProxy("test.jpg");
            outputStream.reset();
            
            // Act
            String info = proxy.getInfo();
            String output = outputStream.toString();
            
            // Assert
            assertThat(info).contains("test.jpg", "unknown size");
            assertThat(output).doesNotContain("Loading image from disk");
        }
    }
    
    @Nested
    @DisplayName("Real Image Direct Tests")
    class RealImageDirectTests {
        
        @Test
        @DisplayName("Real image should load immediately")
        void realImage_shouldLoadImmediately() {
            // Act
            Image image = new RealImage("direct.jpg");
            String output = outputStream.toString();
            
            // Assert
            assertThat(output).contains("Loading image from disk: direct.jpg");
        }
        
        @Test
        @DisplayName("Real image should display without proxy overhead")
        void realImage_shouldDisplayWithoutProxyOverhead() {
            // Arrange
            Image image = new RealImage("direct.jpg");
            outputStream.reset();
            
            // Act
            image.display();
            String output = outputStream.toString();
            
            // Assert
            assertThat(output)
                .contains("Displaying: direct.jpg")
                .doesNotContain("[PROXY]");
        }
    }
    
    @Nested
    @DisplayName("Performance Comparison Tests")
    class PerformanceComparisonTests {
        
        @Test
        @DisplayName("Proxy should be faster than real image for creation")
        void proxy_shouldBeFaster_forCreation() {
            // Measure proxy creation
            long proxyStart = System.nanoTime();
            Image proxy = new ImageProxy("large-image.jpg");
            long proxyTime = System.nanoTime() - proxyStart;
            
            outputStream.reset();
            
            // Measure real image creation
            long realStart = System.nanoTime();
            Image real = new RealImage("large-image.jpg");
            long realTime = System.nanoTime() - realStart;
            
            // Assert
            // Real image loads during construction, proxy doesn't
            String output = outputStream.toString();
            assertThat(output).contains("Loading image from disk");
        }
    }
}
