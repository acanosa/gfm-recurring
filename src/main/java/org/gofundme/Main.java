package org.gofundme;

import org.gofundme.cli.Command;
import org.gofundme.cli.resolver.CommandResolver;
import org.gofundme.cli.strategy.AddCampaignCommandExecutorStrategy;
import org.gofundme.cli.strategy.AddDonorCommandExecutorStrategy;
import org.gofundme.cli.strategy.DonateCommandExecutorStrategy;
import org.gofundme.reader.FileReader;
import org.gofundme.reader.SystemInReader;
import org.gofundme.repository.CampaignRepository;
import org.gofundme.repository.DonorRepository;
import org.gofundme.repository.impl.InMemoryCampaignRepository;
import org.gofundme.repository.impl.InMemoryDonorRepository;
import org.gofundme.service.CampaignService;
import org.gofundme.service.DonationService;
import org.gofundme.service.DonorService;
import org.gofundme.service.SummaryService;

import java.io.*;
import java.util.List;

public class Main {

    private static CommandResolver resolver;
    private static SummaryService summaryService;

    public static void main(String[] args) {
        initApplication();

        List<String> commands;
        //file name will be passed as an arg
        if(fileWasPassedAsArg(args)) {
            File inputFile  = new File(args[0]);
            commands = FileReader.readCommandsFromFile(inputFile);
        } else {
            //file contents might also be passed via a cat or Get-Content command, in this case should be read with System.in
            commands = SystemInReader.readCommandsFromSystemIn();
        }

        if (commands.isEmpty() ) {
            System.out.println("No commands found.");
            return;
        }

        for(String command: commands) {
            resolver.resolve(command);
        }

        System.out.println(summaryService.createSummary());
    }

    private static void initApplication() {
        DonorRepository donorRepository = new InMemoryDonorRepository();
        DonorService donorService = new DonorService(donorRepository);
        CampaignRepository campaignRepository = new InMemoryCampaignRepository();
        CampaignService campaignService = new CampaignService(campaignRepository);
        DonationService donationService = new DonationService(donorService, campaignService);
        AddDonorCommandExecutorStrategy addDonorStrategy = new AddDonorCommandExecutorStrategy(donorService);
        AddCampaignCommandExecutorStrategy addCampaignStrategy = new AddCampaignCommandExecutorStrategy(campaignService);
        DonateCommandExecutorStrategy donateStrategy = new DonateCommandExecutorStrategy(donationService);
        resolver = new CommandResolver(addDonorStrategy, addCampaignStrategy, donateStrategy);
        summaryService = new SummaryService(donorService, campaignService);
    }

    private static boolean fileWasPassedAsArg(String[] args) {
        return (args != null && args.length > 0) && args[0].endsWith(".txt");
    }
}