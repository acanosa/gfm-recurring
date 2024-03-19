
package org.gofundme.service;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donation;
import org.gofundme.model.Donor;
import org.gofundme.repository.CampaignRepository;
import org.gofundme.repository.DonorRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DonationService {

    private DonorService donorService;
    private CampaignService campaignService;

    public DonationService(DonorService donorService, CampaignService campaignService) {
        this.donorService = donorService;
        this.campaignService = campaignService;
    }

    public void donate(String donorName, String campaignName, BigDecimal amount) {
        try {
            Donor donor = donorService.getByName(donorName);
            Campaign campaign = campaignService.getByName(campaignName);
            LocalDateTime donationDate = LocalDateTime.now();

            Donation donation = new Donation(donor, amount, donationDate);
            campaign.getDonations().add(donation);
            donor.setDonationQuantity(donor.getDonationQuantity() + 1);
            donor.setTotalDonated(donor.getTotalDonated().add(amount));

            campaignService.updateCampaign(campaign);
            donorService.updateDonor(donor);
        } catch(Exception ex) {
            System.out.println("Could not complete donation: " + ex.getMessage());
        }

    }

}
