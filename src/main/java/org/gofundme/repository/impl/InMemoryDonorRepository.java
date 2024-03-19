package org.gofundme.repository.impl;

import org.gofundme.model.Campaign;
import org.gofundme.model.Donor;
import org.gofundme.repository.DonorRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class InMemoryDonorRepository implements DonorRepository {

    private List<Donor> donors;

    public InMemoryDonorRepository() {
        this.donors = new ArrayList<>();
    }

    @Override
    public void addDonor(String name, BigDecimal monthlyLimit) {
        Long id = getNewId();
        Donor newDonor = new Donor(id, name, monthlyLimit);
        donors.add(newDonor);
    }

    @Override
    public Optional<Donor> getByName(String name) {
        return donors.stream().filter(element -> element.getName().equalsIgnoreCase(name)).findAny();
    }

    @Override
    public void update(Donor newDonor) {
        Donor oldDonor = donors.stream().filter(element -> element.getName()
                .equalsIgnoreCase(newDonor.getName())).findFirst().orElseThrow(() -> new NoSuchElementException("Donor not found"));
        int index = donors.indexOf(oldDonor);

        donors.set(index, newDonor);
    }

    public List<Donor> getAll() {
        return donors;
    }

    private Long getNewId() {
        return donors.isEmpty() ? 1 : donors.get(donors.size() - 1).getId() + 1;
    }
}
