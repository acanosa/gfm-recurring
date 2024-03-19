package org.gofundme.cli.strategy;

import org.gofundme.service.CampaignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddCampaignCommandExecutorStrategyTest {

    public static final String CAMPAIGN_NAME = "testCampaign";
    private AddCampaignCommandExecutorStrategy strategy;

    @Mock
    private CampaignService campaignServiceMock;

    @BeforeEach
    void setUp() {
        strategy = new AddCampaignCommandExecutorStrategy(campaignServiceMock);
    }

    @Test
    void should_invokeServiceMethod_when_commandIsValid() {
        String command = "Add Campaign testCampaign";

        Mockito.doNothing().when(campaignServiceMock).addCampaign(CAMPAIGN_NAME);

        strategy.executeCommand(command);
        verify(campaignServiceMock, times(1)).addCampaign(CAMPAIGN_NAME);
    }

    @Test
    void should_notInvokeServiceMethod_when_commandIsIncomplete() {
        String command = "Add Campaign";

        strategy.executeCommand(command);
        verifyNoInteractions(campaignServiceMock);
    }
}
