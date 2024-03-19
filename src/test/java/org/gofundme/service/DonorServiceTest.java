package org.gofundme.service;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donation;
import org.gofundme.model.Donor;
import org.gofundme.repository.CampaignRepository;
import org.gofundme.repository.DonorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DonorServiceTest {

    public static final BigDecimal MONTHLY_LIMIT = new BigDecimal("10000");
    private final String DONOR_NAME = "test";
    private final String DONOR_NAME_2 = "test2";
    private DonorService donorService;

    @Mock
    private DonorRepository repositoryMock;

    @BeforeEach
    void setUp() {
        donorService = new DonorService(repositoryMock);
    }

    @Test
    void should_notThrowException_when_savingDonor() {
        doNothing().when(repositoryMock).addDonor(anyString(), any(BigDecimal.class));
        Assertions.assertDoesNotThrow(() -> donorService.addDonor(DONOR_NAME, MONTHLY_LIMIT));
        verify(repositoryMock, times(1)).addDonor(DONOR_NAME, MONTHLY_LIMIT);
    }

    @Test
    void should_continueExecution_when_savingDonorWithANameThatIsAlreadySaved() {
        when(repositoryMock.getByName(anyString())).thenReturn(Optional.of(new Donor(DONOR_NAME, MONTHLY_LIMIT)));
        Assertions.assertDoesNotThrow(() -> donorService.addDonor(DONOR_NAME, MONTHLY_LIMIT));
        verify(repositoryMock, times(0 )).addDonor(anyString(), any());
    }

    @Test
    void should_getDonorByName_when_donorExists() {
        when(repositoryMock.getByName(anyString())).thenReturn(Optional.of(new Donor(DONOR_NAME, MONTHLY_LIMIT)));

        Donor donor = donorService.getByName(DONOR_NAME);

        Assertions.assertNotNull(donor);
    }

    @Test
    void should_getAll_when_executingMethod() {
        when(repositoryMock.getAll()).thenReturn(List.of(new Donor(DONOR_NAME, MONTHLY_LIMIT)
                , new Donor(DONOR_NAME_2, new BigDecimal("15000"))));

        List<Donor> donors = donorService.getAll();

        int expectedSize = 2;
        assertEquals(expectedSize, donors.size());
    }

    @Test
    void should_updateDonor_when_DonorExists() {
        when(repositoryMock.getByName(anyString())).thenReturn(Optional.of(new Donor(DONOR_NAME, MONTHLY_LIMIT)));
        doNothing().when(repositoryMock).update(any());
        Donor donor = donorService.getByName(DONOR_NAME);

        donor.setDonationQuantity(1L);

        donorService.updateDonor(donor);

        donor = donorService.getByName(DONOR_NAME);

        int expectedDonationsQuantity = 1;
        assertEquals(expectedDonationsQuantity, donor.getDonationQuantity());
    }

    @Test
    void should_throwException_when_donorDoesNotExist() {
        doThrow(NoSuchElementException.class).when(repositoryMock).update(any());
        Donor donor = new Donor(DONOR_NAME, MONTHLY_LIMIT);

        assertThrows(NoSuchElementException.class, () -> donorService.updateDonor(donor));
    }

}
