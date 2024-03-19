package org.gofundme.repository;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donation;
import org.gofundme.repository.impl.InMemoryCampaignRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InMemoryCampaignRepositoryTest {

    private final String CAMPAIGN_NAME = "testCampaign";
    private final String CAMPAIGN_NAME_2 = "testCampaign2";
    private CampaignRepository campaignRepository;

    @BeforeEach
    void setUp() {
        campaignRepository = new InMemoryCampaignRepository();
    }

    @Test
    void should_notThrowException_when_savingCampaign() {
        Assertions.assertDoesNotThrow(() -> campaignRepository.addCampaign(CAMPAIGN_NAME));
    }

    @Test
    void should_getCampaignByName_when_campaignExists() {
        campaignRepository.addCampaign(CAMPAIGN_NAME);

        Optional<Campaign> campaign = campaignRepository.getByName(CAMPAIGN_NAME);

        Assertions.assertTrue(campaign.isPresent());
    }

    @Test
    void should_getAll_when_executingMethod() {
        campaignRepository.addCampaign(CAMPAIGN_NAME);
        campaignRepository.addCampaign(CAMPAIGN_NAME_2);

        List<Campaign> campaigns = campaignRepository.getAll();

        int expectedSize = 2;
        assertEquals(expectedSize, campaigns.size());
    }

    @Test
    void should_updateDonor_when_donorExists() {
        campaignRepository.addCampaign(CAMPAIGN_NAME);
        Campaign campaign = campaignRepository.getByName(CAMPAIGN_NAME).get();

        campaign.setDonations(List.of(new Donation()));

        campaignRepository.updateCampaign(campaign);

        campaign = campaignRepository.getByName(CAMPAIGN_NAME).get();

        int expectedDonationsQuantity = 1;
        assertEquals(expectedDonationsQuantity, campaign.getDonations().size());
    }

    @Test
    void should_throwException_when_donorDoesNotExist() {
        Campaign campaign = new Campaign(CAMPAIGN_NAME);

        assertThrows(NoSuchElementException.class, () -> campaignRepository.updateCampaign(campaign));
    }

}
