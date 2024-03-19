package org.gofundme.cli.strategy;

import org.gofundme.cli.CommandExecutor;
import org.gofundme.service.DonationService;
import org.gofundme.service.DonorService;

import java.math.BigDecimal;

public class DonateCommandExecutorStrategy implements CommandExecutor {

    private DonationService donationService;

    public DonateCommandExecutorStrategy(DonationService donationService) {
        this.donationService = donationService;
    }

    @Override
    public void executeCommand(String command) {
        String[] commandParts = command.split(" ");
        
        if(commandParts.length < 4) {
            System.out.println("Invalid command: expecting donor's name, campaign name and amount. Example: Donate Greg SaveTheDogs $100");
        } else {
            String donorName = commandParts[1];
            String campaignName = commandParts[2];
            BigDecimal amount = new BigDecimal(commandParts[3].replace("$", ""));

            donationService.donate(donorName, campaignName, amount);
        }

    }
}
