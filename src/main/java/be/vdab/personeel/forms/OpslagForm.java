package be.vdab.personeel.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class OpslagForm {

    @NotNull
    @Positive
    @Min(1)
    private BigDecimal bedrag;

    public OpslagForm(@NotNull @Positive @Min(1) BigDecimal bedrag) {
        this.bedrag = bedrag;
    }

    public BigDecimal getBedrag() {
        return bedrag;
    }
}
