package org.gofundme.cli.strategy;

import org.gofundme.cli.CommandExecutor;
import org.gofundme.service.CampaignService;
import org.gofundme.service.DonorService;

import java.math.BigDecimal;

public class AddDonorCommandExecutorStrategy implements CommandExecutor {

    private DonorService donorService;

    public AddDonorCommandExecutorStrategy(DonorService donorService) {
        this.donorService = donorService;
    }

    @Override
    public void executeCommand(String command) {
        String[] commandParts = command.split(" ");
        
        if(commandParts.length < 4) {
            System.out.println("Invalid add command: expecting donor's name and monthly limit. Example: Add Donor Greg $100");
        } else {
            String name = commandParts[2];
            BigDecimal amount = new BigDecimal(commandParts[3].replace("$", ""));

            donorService.addDonor(name, amount);
        }

    }
}
