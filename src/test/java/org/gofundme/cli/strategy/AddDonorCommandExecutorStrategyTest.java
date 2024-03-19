package org.gofundme.cli.strategy;

import org.gofundme.service.DonorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddDonorCommandExecutorStrategyTest {

    public static final String DONOR_NAME = "test";
    public static final BigDecimal MONTHLY_LIMIT = new BigDecimal("10000");
    private AddDonorCommandExecutorStrategy strategy;

    @Mock
    private DonorService donorServiceMock;

    @BeforeEach
    void setUp() {
        strategy = new AddDonorCommandExecutorStrategy(donorServiceMock);
    }

    @Test
    void should_invokeServiceMethod_when_commandIsValid() {
        String command = "Add Donor test $10000";

        Mockito.doNothing().when(donorServiceMock).addDonor(DONOR_NAME, MONTHLY_LIMIT);

        strategy.executeCommand(command);
        verify(donorServiceMock, times(1)).addDonor(DONOR_NAME, MONTHLY_LIMIT);
    }

    @Test
    void should_notInvokeServiceMethod_when_commandIsIncomplete() {
        String command = "Add Donor";

        strategy.executeCommand(command);
        verifyNoInteractions(donorServiceMock);
    }
}
