package be.vdab.personeel.services;

import be.vdab.personeel.domain.Werknemer;

import java.math.BigDecimal;
import java.util.Optional;

public interface WerknemerService {

    Optional<Werknemer> findById(long id);
    void opslagBedrag(long id, BigDecimal bedrag);
    void setRijksregister(long id, long nummer);

}
