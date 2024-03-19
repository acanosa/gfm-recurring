package org.gofundme.cli;

public enum Command {

    ADD("Add"),
    DONATE("Donate");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
