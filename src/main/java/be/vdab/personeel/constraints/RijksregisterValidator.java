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
        Optional<Werknemer> werknemer = werknemerService.findById(form.getId());
        LocalDate date = werknemer.get().getGeboorte();
        long rijksregister = form.getRijksregisternummer();

        if (rijksregister >= MIN_RIJKS && rijksregister <= MAX_RIJKS) {
            long geboortejaar = rijksregister / 1000000000;
            long geboortemaand = (rijksregister / 10000000) % 100;
            long geboortedag = (rijksregister / 100000) % 100;
            if (date.getDayOfMonth() == geboortedag && date.getMonthValue() == geboortemaand && (date.getYear() % 100) == geboortejaar) {

                long eersteNegen = rijksregister / 100L;
                long laatste2Cijfers = rijksregister % 100L;
                return laatste2Cijfers == 97 - eersteNegen % 97;

            }
        }
            return false;
        }
    }

//    @Override
//    public boolean isValid(Long rijksregister, ConstraintValidatorContext context) {
//        if (rijksregister == null) {
//            return true;
//        }
//        if (rijksregister >= MIN_RIJKS && rijksregister <= MAX_RIJKS) {
////            long geboortejaar = rijksregister/1000000000;
////            long geboortemaand = (rijksregister/10000000)%100;
////            long geboortedag = (rijksregister/100000)%100;
////        if(geboorte.getDayOfMonth() == geboortedag && geboorte.getMonthValue() == geboortemaand && (geboorte.getYear()%100) == geboortejaar) {
////            rijksregisternr = nummer;
////
////        }
////
////            long eersteZes = rijksregister / 1000000L;
//
//            long eersteNegen = rijksregister / 100L;
//            long laatste2Cijfers = rijksregister % 100L;
//            return laatste2Cijfers == 97 - eersteNegen % 97;
//        }
//        return false;
//    }


