
package org.gofundme.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Campaign {

    private String name;

    private List<Donation> donations;

    public Campaign() {}

    public Campaign(String name) {
        this.name = name;
        this.donations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                ", name='" + name + '\'' +
                '}';
    }
}
