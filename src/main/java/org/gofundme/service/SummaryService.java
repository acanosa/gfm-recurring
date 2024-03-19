package org.gofundme.service;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donation;
import org.gofundme.model.Donor;

import java.math.BigDecimal;
import java.util.List;

public class SummaryService {

    private DonorService donorService;
    private CampaignService campaignService;

    public SummaryService(DonorService donorService, CampaignService campaignService) {
        this.donorService = donorService;
        this.campaignService = campaignService;
    }

    public String createSummary() {
        StringBuilder builder = new StringBuilder();
        List<Donor> donors = donorService.getAll();
        List<Campaign> campaigns = campaignService.getAll();
        String line;
        builder.append("Donors:\n");
        for(Donor donor: donors) {
            if(donor.getDonationQuantity() == 0) {
                line = donor.getName().concat(": Total: $0 Average $0\n");
            } else {
                line = donor.getName().concat(": Total: $").concat(donor.getTotalDonated().toString())
                        .concat(" Average: $").concat(donor.getTotalDonated()
                                .divide(BigDecimal.valueOf(donor.getDonationQuantity())).toString()).concat("\n");
            }
            builder.append(line);
        }
        builder.append("\n");
        builder.append("\n");
        builder.append("Campaigns: \n");

        for(Campaign campaign : campaigns) {
            BigDecimal totalDonations = campaign.getDonations().stream().map(Donation::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            builder.append(campaign.getName().concat(": Total: $".concat(totalDonations.toString()).concat("\n")));
        }

        return builder.toString();
    }
}
