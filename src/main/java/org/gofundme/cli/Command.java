package org.gofundme.cli;

public enum Command {

    ADD("Add"),
    DONATE("Donate"),
    EXIT("Exit");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
