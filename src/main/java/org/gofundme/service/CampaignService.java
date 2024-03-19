package org.gofundme.service;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donor;
import org.gofundme.repository.CampaignRepository;
import org.gofundme.repository.DonorRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CampaignService {

    private CampaignRepository repository;

    public CampaignService(CampaignRepository repository) {
        this.repository = repository;
    }

    public void addCampaign(String name) {
        Optional<Campaign> campaign = repository.getByName(name);

        if(campaign.isPresent()) {
            System.out.println("A campaign with that name already exists! Try using another");
        } else {
            repository.addCampaign(name);
        }
    }

    public List<Campaign> getAll() {
        return repository.getAll();
    }

    public Campaign getByName(String name) {
        return repository.getByName(name).orElseThrow(() -> new NoSuchElementException("Campaign does not exist!"));
    }

    public void updateCampaign(Campaign campaign) {
        repository.updateCampaign(campaign);
    }

}
