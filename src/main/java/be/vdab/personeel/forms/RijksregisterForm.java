package be.vdab.personeel.forms;

import be.vdab.personeel.constraints.Rijksregister;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Rijksregister
public class RijksregisterForm {

    @NotNull
    private Long rijksId;
    @NotNull
    private String rijksregisternummer;

    public RijksregisterForm(@NotNull Long rijksId, @NotNull String rijksregisternummer) {
        this.rijksId =rijksId;
        this.rijksregisternummer = rijksregisternummer;
    }

    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public Long getrijksId() {
        return rijksId;
    }
}
