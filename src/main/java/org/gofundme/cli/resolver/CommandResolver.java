package org.gofundme.cli.resolver;

import org.gofundme.cli.Command;
import org.gofundme.cli.strategy.AddCampaignCommandExecutorStrategy;
import org.gofundme.cli.strategy.AddDonorCommandExecutorStrategy;
import org.gofundme.cli.strategy.DonateCommandExecutorStrategy;

public class CommandResolver {

    private final String DONOR = "Donor";
    private final String CAMPAIGN = "Campaign";

    private AddDonorCommandExecutorStrategy addDonorStrategy;

    private AddCampaignCommandExecutorStrategy addCampaignStrategy;
    private DonateCommandExecutorStrategy donateCommandExecutorStrategy;

    public CommandResolver(AddDonorCommandExecutorStrategy addDonorStrategy,
                           AddCampaignCommandExecutorStrategy addCampaignCommandExecutorStrategy,
                           DonateCommandExecutorStrategy donateCommandExecutorStrategy) {
        this.addDonorStrategy = addDonorStrategy;
        this.addCampaignStrategy = addCampaignCommandExecutorStrategy;
        this.donateCommandExecutorStrategy = donateCommandExecutorStrategy;
    }

    public void resolve(String command) {
        if(command.startsWith(Command.ADD.getValue())) {

            String objectToCreate = command.split(" ")[1];

            if(objectToCreate.equalsIgnoreCase(DONOR)) {
                addDonorStrategy.executeCommand(command);
            } else if(objectToCreate.equalsIgnoreCase(CAMPAIGN)) {
                addCampaignStrategy.executeCommand(command);
            }
        } else if(command.startsWith(Command.DONATE.getValue())) {
            donateCommandExecutorStrategy.executeCommand(command);
        }
    }



}
