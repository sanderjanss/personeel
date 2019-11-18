package be.vdab.personeel.services;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultWerknemerService implements WerknemerService {
    private final WerknemerRepository werknemerRepository;

    public DefaultWerknemerService(WerknemerRepository werknemerRepository) {
        this.werknemerRepository = werknemerRepository;
    }

    @Override
    public Optional<Werknemer> findById(long id) {
        return werknemerRepository.findById(id);
    }



    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void opslagBedrag(long id, BigDecimal bedrag) {
        werknemerRepository.findById(id).ifPresent(werknemer -> werknemer.opslag(bedrag));
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void setRijksregister(long id, String nummer) {
        werknemerRepository.findById(id).ifPresent(werknemer -> werknemer.setRijksregisternr(Long.parseLong(nummer)));
    }


}
