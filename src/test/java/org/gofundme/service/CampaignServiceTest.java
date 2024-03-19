package org.gofundme.service;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donation;
import org.gofundme.repository.CampaignRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {

    private final String CAMPAIGN_NAME = "testCampaign";
    private final String CAMPAIGN_NAME_2 = "testCampaign2";
    private CampaignService campaignService;

    @Mock
    private CampaignRepository repositoryMock;

    @BeforeEach
    void setUp() {
        campaignService = new CampaignService(repositoryMock);
    }

    @Test
    void should_notThrowException_when_savingCampaign() {
        doNothing().when(repositoryMock).addCampaign(anyString());
        Assertions.assertDoesNotThrow(() -> campaignService.addCampaign(CAMPAIGN_NAME));
        verify(repositoryMock, times(1)).addCampaign(CAMPAIGN_NAME);
    }

    @Test
    void should_continueExecution_when_savingCampaignWithANameThatIsAlreadySaved() {
        when(repositoryMock.getByName(anyString())).thenReturn(Optional.of(new Campaign(CAMPAIGN_NAME)));
        Assertions.assertDoesNotThrow(() -> campaignService.addCampaign(CAMPAIGN_NAME));
        verify(repositoryMock, times(0 )).addCampaign(anyString());
    }

    @Test
    void should_getCampaignByName_when_campaignExists() {
        when(repositoryMock.getByName(anyString())).thenReturn(Optional.of(new Campaign(CAMPAIGN_NAME)));

        Campaign campaign = campaignService.getByName(CAMPAIGN_NAME);

        Assertions.assertNotNull(campaign);
    }

    @Test
    void should_getAll_when_executingMethod() {
        when(repositoryMock.getAll()).thenReturn(List.of(new Campaign(CAMPAIGN_NAME), new Campaign(CAMPAIGN_NAME_2)));

        List<Campaign> campaigns = campaignService.getAll();

        int expectedSize = 2;
        assertEquals(expectedSize, campaigns.size());
    }

    @Test
    void should_updateCampaign_when_campaignExists() {
        when(repositoryMock.getByName(anyString())).thenReturn(Optional.of(new Campaign(CAMPAIGN_NAME)));
        doNothing().when(repositoryMock).updateCampaign(any());
        Campaign campaign = campaignService.getByName(CAMPAIGN_NAME);

        campaign.setDonations(List.of(new Donation()));

        campaignService.updateCampaign(campaign);

        campaign = campaignService.getByName(CAMPAIGN_NAME);

        int expectedDonationsQuantity = 1;
        assertEquals(expectedDonationsQuantity, campaign.getDonations().size());
    }

    @Test
    void should_throwException_when_campaignDoesNotExist() {
        doThrow(NoSuchElementException.class).when(repositoryMock).updateCampaign(any());
        Campaign campaign = new Campaign(CAMPAIGN_NAME);

        assertThrows(NoSuchElementException.class, () -> campaignService.updateCampaign(campaign));
    }

}
