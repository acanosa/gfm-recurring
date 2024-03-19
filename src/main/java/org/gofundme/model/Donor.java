package org.gofundme.model;

import java.math.BigDecimal;

public class Donor {

    private Long id;
    private String name;
    private BigDecimal monthlyLimit;
    private Long donationQuantity;
    private BigDecimal totalDonated;
    private BigDecimal averageDonations;

    public Donor() {}

    public Donor(Long id, String name, BigDecimal monthlyLimit) {
        this.id = id;
        this.name = name;
        this.monthlyLimit = monthlyLimit;
        this.totalDonated = BigDecimal.ZERO;
        this.averageDonations = BigDecimal.ZERO;
        this.donationQuantity = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public Long getDonationQuantity() {
        return donationQuantity;
    }

    public void setDonationQuantity(Long donationQuantity) {
        this.donationQuantity = donationQuantity;
    }

    public BigDecimal getTotalDonated() {
        return totalDonated;
    }

    public void setTotalDonated(BigDecimal totalDonated) {
        this.totalDonated = totalDonated;
    }

    public BigDecimal getAverageDonations() {
        return averageDonations;
    }

    public void setAverageDonations(BigDecimal averageDonations) {
        this.averageDonations = averageDonations;
    }

    @Override
    public String toString() {
        return name.concat(": Total: $").concat(totalDonated.toString()).concat(" Average: $").concat(totalDonated.divide(BigDecimal.valueOf(donationQuantity)).toString());
    }
}
