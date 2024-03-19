package org.gofundme.repository;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CampaignRepository {

    Optional<Campaign> getByName(String name);

    List<Campaign> getAll();

    void addCampaign(String name);

    void updateCampaign(Campaign campaign);

}
