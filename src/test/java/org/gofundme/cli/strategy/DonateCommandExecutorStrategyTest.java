package org.gofundme.cli.strategy;

import org.gofundme.service.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DonateCommandExecutorStrategyTest {

    public static final String DONOR_NAME = "test";
    public static final String CAMPAIGN_NAME = "testCampaign";
    public static final BigDecimal AMOUNT = new BigDecimal("10");
    private DonateCommandExecutorStrategy strategy;

    @Mock
    private DonationService donationServiceMock;

    @BeforeEach
    void setUp() {
        strategy = new DonateCommandExecutorStrategy(donationServiceMock);
    }

    @Test
    void should_invokeServiceMethod_when_commandIsValid() {
        String command = "Donate test testCampaign $10";

        Mockito.doNothing().when(donationServiceMock).donate(DONOR_NAME, CAMPAIGN_NAME, AMOUNT);

        strategy.executeCommand(command);
        verify(donationServiceMock, times(1)).donate(DONOR_NAME, CAMPAIGN_NAME, AMOUNT);
    }

    @Test
    void should_notInvokeServiceMethod_when_commandIsIncomplete() {
        String command = "Donate";

        strategy.executeCommand(command);
        verifyNoInteractions(donationServiceMock);
    }
}
