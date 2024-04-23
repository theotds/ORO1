package com.carshop2;

import com.carshop2.entities.Part;
import com.carshop2.entities.Transaction;
import com.carshop2.entities.User;
import com.carshop2.repositories.PartRepository;
import com.carshop2.repositories.TransactionRepository;
import com.carshop2.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class TransactionRepositoryTests {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartRepository partRepository;

    private User user1;
    private User user2;

    @BeforeEach
    public void setup() {

        user1 = new User();
        user1.setEmail("user1@example.com");
        userRepository.save(user1);

        user2 = new User();
        user2.setEmail("user2@example.com");
        userRepository.save(user2);

        Part part1 = new Part();
        part1.setName("Part1");
        part1.setPrice(100.00);
        partRepository.save(part1);

        Part part2 = new Part();
        part2.setName("Part2");
        part2.setPrice(50.00);
        partRepository.save(part2);

        Transaction transaction1 = new Transaction(user1, part1);
        Transaction transaction2 = new Transaction(user2, part2);
        Transaction transaction3 = new Transaction(user2, part1);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);
    }

    //Liczba transakcji
    @Test
    public void whenCountAllTransactions_thenCorrectNumber() {
        Long count = transactionRepository.count();
        assertEquals(3, count, "Should return the total number of transactions");
    }

    //Liczba transakcji konkretnego produktu
    @Test
    public void whenCountTransactionsByPartId_thenCorrectNumber() {
        Optional<Part> part1Optional = partRepository.findByName("Part1");
        assertTrue(part1Optional.isPresent(), "Part should be found");

        Part part1 = part1Optional.get();
        long count = transactionRepository.countByPartId(part1.getId());
        assertEquals(2, count, "Should return the number of transactions for the specified part");
    }

    //Liczba transakcji konkretnego użytkownika
    @Test
    public void whenCountTransactionsByUserId_thenCorrectNumber() {
        long count = transactionRepository.countByUserId(user1.getId());
        assertEquals(1, count, "Should return the number of transactions for the specified user");
    }

    @Test
    public void whenCountTransactionsByUserId2_thenCorrectNumber() {
        long count = transactionRepository.countByUserId(user2.getId());
        assertEquals(2, count, "Should return the number of transactions for the specified user");
    }

}