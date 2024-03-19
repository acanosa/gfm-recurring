package org.gofundme.repository;

import org.gofundme.model.Donor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DonorRepository {

    void addDonor(String name, BigDecimal monthlyLimit);

    Optional<Donor> getByName(String name);

    List<Donor> getAll();

    void update(Donor donor);

}
