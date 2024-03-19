package org.gofundme.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Donation {

   private Donor donor;
   private BigDecimal amount;
   private LocalDateTime date;

    public Donation(Donor donor, BigDecimal amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
        this.donor = donor;
    }

    public Donation() {}

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "donor=" + donor +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
