package be.vdab.personeel.forms;

import be.vdab.personeel.constraints.Rijksregister;

import javax.validation.constraints.NotNull;

@Rijksregister
public class RijksregisterForm {

    private long id;
    @NotNull
    private long rijksregisternummer;

    public RijksregisterForm(long id, @NotNull long rijksregisternummer) {

        this.rijksregisternummer = rijksregisternummer;
    }

    public long getRijksregisternummer() {
        return rijksregisternummer;
    }

    public long getId() {
        return id;
    }
}
