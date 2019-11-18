package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Werknemer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
  //  List<Werknemer> findWerknemersByChef();
//    @Modifying(clearAutomatically = true)
////    @Query("update Werknemer w SET w.salaris = w.salaris + :bedrag where w.id = :id")
////    int updateSalaris(@Param("bedrag")BigDecimal bedrag, @Param("id") long id);

    @EntityGraph(Werknemer.MET_JOBTITEL)
    Optional<Werknemer> findById(long id);
}
