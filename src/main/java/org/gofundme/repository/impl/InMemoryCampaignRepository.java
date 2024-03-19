package org.gofundme.repository.impl;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donor;
import org.gofundme.repository.CampaignRepository;
import org.gofundme.repository.DonorRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class InMemoryCampaignRepository implements CampaignRepository {

    private List<Campaign> campaigns;

    public InMemoryCampaignRepository() {
        this.campaigns = new ArrayList<>();
    }

    @Override
    public List<Campaign> getAll() {
        return campaigns;
    }

    @Override
    public void addCampaign(String name) {
        Campaign newCampaign = new Campaign(name);
        campaigns.add(newCampaign);
    }

    @Override
    public void updateCampaign(Campaign newCampaign) {
        Campaign oldCampaign = campaigns.stream().filter(element -> element.getName()
                .equalsIgnoreCase(newCampaign.getName())).findFirst().orElseThrow(() -> new NoSuchElementException("Campaign not found"));
        int index = campaigns.indexOf(oldCampaign);

        campaigns.set(index, newCampaign);
    }

    public Optional<Campaign> getByName(String name) {
        return campaigns.stream().filter(element -> element.getName().equalsIgnoreCase(name)).findAny();
    }

}
