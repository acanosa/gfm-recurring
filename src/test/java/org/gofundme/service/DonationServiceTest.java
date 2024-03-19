package org.gofundme.service;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donor;
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
public class DonationServiceTest {

    public static final BigDecimal MONTHLY_LIMIT = new BigDecimal("10000");
    public static final BigDecimal DONATION_AMOUNT = new BigDecimal("100");
    private final String DONOR_NAME = "test";
    private final String CAMPAIGN_NAME = "testCampaign";
    private DonationService donationService;

    @Mock
    private DonorService donorServiceMock;

    @Mock
    private CampaignService campaignServiceMock;

    @BeforeEach
    void setUp() {
        donationService = new DonationService(donorServiceMock, campaignServiceMock);
    }

    @Test
    void should_registerDonationAndUpdateDonorStatus_when_dataIsValid() {
        when(donorServiceMock.getByName(DONOR_NAME)).thenReturn(new Donor(DONOR_NAME, MONTHLY_LIMIT));
        when(campaignServiceMock.getByName(CAMPAIGN_NAME)).thenReturn(new Campaign(CAMPAIGN_NAME));

        Assertions.assertDoesNotThrow(() -> donationService.donate(DONOR_NAME, CAMPAIGN_NAME, DONATION_AMOUNT));
        verify(donorServiceMock, times(1)).updateDonor(any());
        verify(campaignServiceMock, times(1)).updateCampaign(any());
    }

    @Test
    void should_notRegisterDonation_when_exceptionIsThrown() {
        when(donorServiceMock.getByName(DONOR_NAME)).thenThrow(new RuntimeException("test"));

        Assertions.assertDoesNotThrow(() -> donationService.donate(DONOR_NAME, CAMPAIGN_NAME, DONATION_AMOUNT));
        verify(donorServiceMock, times(0)).updateDonor(any());
        verify(campaignServiceMock, times(0)).updateCampaign(any());
    }

}
