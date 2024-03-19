package org.gofundme.service;

import org.gofundme.model.Donor;
import org.gofundme.repository.DonorRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DonorService {

    private DonorRepository repository;

    public DonorService(DonorRepository repository) {
        this.repository = repository;
    }

    public void addDonor(String name, BigDecimal monthlyLimit) {
        Optional<Donor> donor = repository.getByName(name);

        if(donor.isPresent()) {
            System.out.println("The donor already exists! Try using another name");
        } else {
            repository.addDonor(name, monthlyLimit);
        }
    }

    public Donor getByName(String name) {
        return repository.getByName(name).orElseThrow(() -> new NoSuchElementException("Donor does not exist!"));
    }

    public void updateDonor(Donor donor) {
        repository.update(donor);
    }

    public List<Donor> getAll() {
        return repository.getAll();
    }

}
