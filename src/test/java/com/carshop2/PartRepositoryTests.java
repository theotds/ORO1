package com.carshop2;

import com.carshop2.entities.Part;
import com.carshop2.repositories.PartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // Ensures each test is rolled back after execution
public class PartRepositoryTests {

    @Autowired
    private PartRepository partRepository;

    @Test
    public void whenFindByPriceBetween_thenReturnParts() {
        // Arrange
        Part brakePad = new Part("Brake Pad", 29.99);
        partRepository.save(brakePad);
        Part brakeDisc = new Part("Brake Disc", 45.99);
        partRepository.save(brakeDisc);

        // Act
        List<Part> foundParts = partRepository.findByPriceBetween(20.00, 30.00);

        // Assert
        assertThat(foundParts).hasSize(1).extracting(Part::getName).containsOnly("Brake Pad");
    }

    @Test
    public void whenPartSaved_thenFindById() {
        // Arrange
        Part newPart = new Part("Air Filter", 19.99);
        partRepository.save(newPart);
        Long newPartId = newPart.getId();

        // Act
        Part foundPart = partRepository.findById(newPartId).orElse(null);

        // Assert
        assertThat(foundPart).isNotNull();
        assertThat(foundPart.getName()).isEqualTo("Air Filter");
        assertThat(foundPart.getPrice()).isEqualTo(19.99);
    }
}
