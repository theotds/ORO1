package com.carshop2;

import com.carshop2.entities.Part;
import com.carshop2.repositories.PartRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PartRepositoryTests {

    @Autowired
    private PartRepository partRepository;

    @BeforeEach
    public void setup() {
        Part part1 = new Part("Part1", 50.00);
        Part part2 = new Part("Part2", 150.00);
        partRepository.save(part1);
        partRepository.save(part2);
    }

    //Produkty według przedziału cen
    @Test
    public void whenFindByPriceRange_thenPartsReturned() {
        List<Part> foundParts = partRepository.findByPriceBetween(40.00, 100.00);
        assertFalse(foundParts.isEmpty(), "Should find parts within price range");
        assertTrue(foundParts.stream().anyMatch(part -> part.getName().equals("Part1")), "Part1 should be included in the results");
    }
}
