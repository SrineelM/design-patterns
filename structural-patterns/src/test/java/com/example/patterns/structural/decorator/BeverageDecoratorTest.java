package com.example.patterns.structural.decorator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for Decorator pattern implementation.
 */
@DisplayName("Decorator Pattern Tests")
class BeverageDecoratorTest {
    
    @Nested
    @DisplayName("Basic Beverage Tests")
    class BasicBeverageTests {
        
        @Test
        @DisplayName("Espresso should have correct cost and description")
        void espresso_shouldHaveCorrectCostAndDescription() {
            // Arrange & Act
            Beverage espresso = new Espresso();
            
            // Assert
            assertThat(espresso.getDescription()).isEqualTo("Espresso (MEDIUM)");
            assertThat(espresso.getCost()).isEqualTo(1.99);
        }
        
        @Test
        @DisplayName("Dark roast should have correct cost and description")
        void darkRoast_shouldHaveCorrectCostAndDescription() {
            // Arrange & Act
            Beverage darkRoast = new DarkRoast();
            
            // Assert
            assertThat(darkRoast.getDescription()).isEqualTo("Dark Roast Coffee (MEDIUM)");
            assertThat(darkRoast.getCost()).isEqualTo(0.99);
        }
    }
    
    @Nested
    @DisplayName("Single Decorator Tests")
    class SingleDecoratorTests {
        
        @Test
        @DisplayName("Espresso with mocha should add cost and description")
        void espressoWithMocha_shouldAddCostAndDescription() {
            // Arrange
            Beverage beverage = new Espresso();
            
            // Act
            beverage = new Mocha(beverage);
            
            // Assert
            assertThat(beverage.getDescription()).isEqualTo("Espresso (MEDIUM), Mocha");
            assertThat(beverage.getCost()).isEqualTo(1.99 + 0.20);
        }
        
        @Test
        @DisplayName("Dark roast with whip should add cost and description")
        void darkRoastWithWhip_shouldAddCostAndDescription() {
            // Arrange
            Beverage beverage = new DarkRoast();
            
            // Act
            beverage = new Whip(beverage);
            
            // Assert
            assertThat(beverage.getDescription()).isEqualTo("Dark Roast Coffee (MEDIUM), Whip");
            assertThat(beverage.getCost()).isEqualTo(0.99 + 0.10);
        }
    }
    
    @Nested
    @DisplayName("Multiple Decorator Tests")
    class MultipleDecoratorTests {
        
        @Test
        @DisplayName("Should apply multiple decorators correctly")
        void shouldApplyMultipleDecorators() {
            // Arrange
            Beverage beverage = new Espresso();
            
            // Act
            beverage = new Mocha(beverage);
            beverage = new Whip(beverage);
            beverage = new Caramel(beverage);
            
            // Assert
            assertThat(beverage.getDescription())
                .isEqualTo("Espresso (MEDIUM), Mocha, Whip, Caramel");
            assertThat(beverage.getCost()).isEqualTo(1.99 + 0.20 + 0.10 + 0.15);
        }
        
        @Test
        @DisplayName("Should apply same decorator multiple times")
        void shouldApplySameDecoratorMultipleTimes() {
            // Arrange
            Beverage beverage = new HouseBlend();
            
            // Act
            beverage = new Mocha(beverage);
            beverage = new Mocha(beverage);
            
            // Assert
            assertThat(beverage.getDescription())
                .isEqualTo("House Blend Coffee (MEDIUM), Mocha, Mocha");
            assertThat(beverage.getCost()).isEqualTo(0.89 + 0.20 + 0.20);
        }
    }
    
    @Nested
    @DisplayName("Size Multiplier Tests")
    class SizeMultiplierTests {
        
        @ParameterizedTest
        @EnumSource(Beverage.Size.class)
        @DisplayName("Should apply size multiplier to base beverage")
        void shouldApplySizeMultiplier_toBaseBeverage(Beverage.Size size) {
            // Arrange & Act
            Beverage beverage = new Espresso(size);
            
            // Assert
            double expectedCost = 1.99 * size.getMultiplier();
            assertThat(beverage.getCost()).isEqualTo(expectedCost);
        }
        
        @Test
        @DisplayName("Should apply size multiplier to decorators")
        void shouldApplySizeMultiplier_toDecorators() {
            // Arrange
            Beverage beverage = new Espresso(Beverage.Size.LARGE);
            
            // Act
            beverage = new Mocha(beverage);
            
            // Assert
            double expectedCost = (1.99 * 1.2) + (0.20 * 1.2);
            assertThat(beverage.getCost()).isEqualTo(expectedCost);
        }
    }
}
