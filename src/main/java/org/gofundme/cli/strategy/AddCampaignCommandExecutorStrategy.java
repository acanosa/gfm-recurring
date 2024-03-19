package org.gofundme.cli.strategy;

import org.gofundme.cli.CommandExecutor;
import org.gofundme.service.CampaignService;
import org.gofundme.service.DonorService;

import java.math.BigDecimal;

public class AddCampaignCommandExecutorStrategy implements CommandExecutor {

    private CampaignService campaignService;

    public AddCampaignCommandExecutorStrategy(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Override
    public void executeCommand(String command) {
        String[] commandParts = command.split(" ");
        
        if(commandParts.length < 3) {
            System.out.println("Invalid add command: expecting campaign's name. Example: Add Campaign SaveTheDogs");
        } else {
            String name = commandParts[2];

            campaignService.addCampaign(name);
        }
    }
}
