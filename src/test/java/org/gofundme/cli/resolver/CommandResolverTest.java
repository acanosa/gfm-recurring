package org.gofundme.cli.resolver;

import org.gofundme.cli.strategy.AddCampaignCommandExecutorStrategy;
import org.gofundme.cli.strategy.AddDonorCommandExecutorStrategy;
import org.gofundme.cli.strategy.DonateCommandExecutorStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommandResolverTest {

    private CommandResolver commandResolver;

    @Mock
    private AddDonorCommandExecutorStrategy addDonorCommandExecutorStrategyMock;

    @Mock
    private AddCampaignCommandExecutorStrategy addCampaignCommandExecutorStrategyMock;

    @Mock
    private DonateCommandExecutorStrategy donateCommandExecutorStrategyMock;

    @BeforeEach
    void setUp() {
        commandResolver = new CommandResolver(addDonorCommandExecutorStrategyMock,
                addCampaignCommandExecutorStrategyMock, donateCommandExecutorStrategyMock);
    }

    @Test
    void should_resolveToAddDonor_when_commandIsAddDonor() {
        String command = "Add Donor test $100";

        doNothing().when(addDonorCommandExecutorStrategyMock).executeCommand(command);

        commandResolver.resolve(command);
        verify(addDonorCommandExecutorStrategyMock, times(1)).executeCommand(command);
    }

    @Test
    void should_resolveToAddCampaign_when_commandIsAddCampaign() {
        String command = "Add Campaign testCampaign";

        doNothing().when(addCampaignCommandExecutorStrategyMock).executeCommand(command);

        commandResolver.resolve(command);
        verify(addCampaignCommandExecutorStrategyMock, times(1)).executeCommand(command);
    }

    @Test
    void should_resolveToDonate_when_commandIsDonate() {
        String command = "Donate test testCampaign $10";

        doNothing().when(donateCommandExecutorStrategyMock).executeCommand(command);

        commandResolver.resolve(command);
        verify(donateCommandExecutorStrategyMock, times(1)).executeCommand(command);
    }

}
