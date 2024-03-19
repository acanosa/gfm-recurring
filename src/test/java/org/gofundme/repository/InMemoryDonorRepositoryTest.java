package org.gofundme.repository;

import org.gofundme.model.Donor;
import org.gofundme.repository.impl.InMemoryDonorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryDonorRepositoryTest {

    public static final BigDecimal MONTHLY_LIMIT = new BigDecimal("10000");
    private final String DONOR_NAME = "test";
    private final String DONOR_NAME_2 = "test2";


    private DonorRepository donorRepository;

    @BeforeEach
    void setUp() {
        donorRepository = new InMemoryDonorRepository();
    }

    @Test
    void should_notThrowException_when_savingDonor() {
        Assertions.assertDoesNotThrow(() -> donorRepository.addDonor(DONOR_NAME, MONTHLY_LIMIT));
    }

    @Test
    void should_getDonorByName_when_donorExists() {
        donorRepository.addDonor(DONOR_NAME, MONTHLY_LIMIT);

        Optional<Donor> donor = donorRepository.getByName(DONOR_NAME);

        Assertions.assertTrue(donor.isPresent());
    }

    @Test
    void should_getAll_when_executingMethod() {
        donorRepository.addDonor(DONOR_NAME, MONTHLY_LIMIT);
        donorRepository.addDonor(DONOR_NAME_2, new BigDecimal("16000"));

        List<Donor> donors = donorRepository.getAll();

        int expectedSize = 2;
        assertEquals(expectedSize, donors.size());
    }

    @Test
    void should_updateDonor_when_donorExists() {
        donorRepository.addDonor(DONOR_NAME, MONTHLY_LIMIT);
        Donor donor = donorRepository.getByName(DONOR_NAME).get();

        donor.setDonationQuantity(5L);
        donor.setTotalDonated(new BigDecimal("5000"));

        donorRepository.update(donor);

        donor = donorRepository.getByName(DONOR_NAME).get();

        int expectedQuantity = 5;
        BigDecimal expectedTotalAmount = new BigDecimal("5000");
        assertEquals(expectedQuantity, donor.getDonationQuantity());
        assertEquals(expectedTotalAmount, donor.getTotalDonated());
    }

    @Test
    void should_throwException_when_donorDoesNotExist() {
        Donor donor = new Donor(DONOR_NAME, MONTHLY_LIMIT);

        assertThrows(NoSuchElementException.class, () -> donorRepository.update(donor));
    }

}
