package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Werknemer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
    @EntityGraph(Werknemer.MET_JOBTITEL)
    Optional<Werknemer> findById(long id);
}
