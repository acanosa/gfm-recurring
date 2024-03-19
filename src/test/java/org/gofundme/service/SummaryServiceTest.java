package org.gofundme.service;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donation;
import org.gofundme.model.Donor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SummaryServiceTest {

    public static final BigDecimal MONTHLY_LIMIT = new BigDecimal("10000");
    public static final BigDecimal DONATION_AMOUNT = new BigDecimal("100");
    private static final String DONOR_NAME = "test";
    private static final String CAMPAIGN_NAME = "testCampaign";

    private static final String EXPECTED_SUMMARY = "Donors:\n" +
            "test: Total: $100 Average: $100\n" +
            "\n" +
            "\n" +
            "Campaigns: \n" +
            "testCampaign: Total: $100\n";

    private SummaryService summaryService;
    @Mock
    private DonorService donorServiceMock;

    @Mock
    private CampaignService campaignServiceMock;

    @BeforeEach
    void setUp() {
        summaryService = new SummaryService(donorServiceMock, campaignServiceMock);
    }

    @Test
    void should_buildSummary_when_invokingMethod() {
        Donor donor = new Donor(DONOR_NAME, MONTHLY_LIMIT);
        donor.setDonationQuantity(1L);
        donor.setTotalDonated(DONATION_AMOUNT);

        Campaign campaign = new Campaign(CAMPAIGN_NAME);
        Donation donation =  new Donation(donor, DONATION_AMOUNT, LocalDateTime.now());
        campaign.setDonations(List.of(donation));
        when(donorServiceMock.getAll()).thenReturn(List.of(donor));
        when(campaignServiceMock.getAll()).thenReturn(List.of(campaign));

        String summary = summaryService.createSummary();

        assertEquals(EXPECTED_SUMMARY, summary);
    }


}
