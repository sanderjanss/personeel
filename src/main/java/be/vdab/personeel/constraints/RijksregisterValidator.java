package be.vdab.personeel.constraints;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.forms.RijksregisterForm;
import be.vdab.personeel.services.WerknemerService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Optional;

public class RijksregisterValidator implements ConstraintValidator<Rijksregister, RijksregisterForm> {
    private static final long MIN_RIJKS = 10000000000L;
    private static final long MAX_RIJKS = 99999999999L;
    private WerknemerService werknemerService;

    public RijksregisterValidator(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }


    @Override
    public void initialize(Rijksregister constraintAnnotation) {

    }

    @Override
    public boolean isValid(RijksregisterForm form, ConstraintValidatorContext constraintValidatorContext) {
        if (form == null) {
            return true;
        }
        Optional<Werknemer> werknemer = werknemerService.findById(form.getrijksId());
        LocalDate date = werknemer.get().getGeboorte();
        long rijksregister = Long.parseLong(form.getRijksregisternummer());

        if (rijksregister >= MIN_RIJKS && rijksregister <= MAX_RIJKS) {
            long geboortejaar = rijksregister / 1000000000;
            long geboortemaand = (rijksregister / 10000000) % 100;
            long geboortedag = (rijksregister / 100000) % 100;
            if (date.getDayOfMonth() == geboortedag && date.getMonthValue() == geboortemaand && (date.getYear() % 100) == geboortejaar) {
                long eersteNegen = rijksregister / 100L;
                long laatste2Cijfers = rijksregister % 100L;
                if (date.getYear() >= 2000){
                    long eersteNegenPlus = eersteNegen + 2000000000;
                    return laatste2Cijfers == 97 - eersteNegenPlus % 97;
                }
                return laatste2Cijfers == 97 - eersteNegen % 97;

            }
        }
        return false;
        }
    }

